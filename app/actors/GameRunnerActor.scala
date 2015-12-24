package actors

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Top level supervisor actor to manage Game Engine and Bots
  */
class GameRunnerActor extends Actor with ActorLogging {
  import GameRunnerActor._
  import GameEngineActor._

  val gameRunnerActor: ActorRef = context.actorOf(GameEngineActor.props, "gameRunner")
  var bots: List[ActorRef] = List()

  override def receive = {
    case Initialize(gameOptions: GameOptions) => {
      gameRunnerActor ! GameEngineActor.GameSetup(gameOptions.gameName)

      1 to gameOptions.playerCount foreach {
        i => {
          val uuid: String = java.util.UUID.randomUUID.toString
          bots = bots :+ context.actorOf(BotActor.props, s"bot$uuid")
        }
      }

      bots.zipWithIndex.foreach {
        case (bot, i) => {
          val botName = bot.path.name
          bot ! BotActor.BotStart(gameOptions.gameName, s"bot$botName")
        }
      }

      gameRunnerActor ! GameEngineActor.GameStart
    }
    case TurnStart(gameState: String) => {
      log.info(s"Got turn start message $gameState. Sending state toward bots")
      implicit val timeout = Timeout(5 seconds)

      val botMoveFutures: Map[String, Future[Any]] = bots.foldLeft(Map[String, Future[Any]]()) {
        (result, bot) => result ++: Map[String, Future[Any]](bot.path.name -> bot ? BotActor.TurnStart(gameState))
      }

      val botMoves: Map[String, String] = botMoveFutures.foldLeft(Map[String, String]()) {
        case (result: Map[String, String], (botName: String, botMove: Future[Any])) =>
          result ++: Map[String, String](
            botName -> Await.result(botMove, 5 seconds).asInstanceOf[String]
          )
      }

      gameRunnerActor ! TurnFinish(botMoves)
    }
    case GameFinish => {
      // clean up bot
      bots foreach {
        bot => bot ! PoisonPill
      }
      bots = List()
      log.info("Game is finished")
    }
  }
}

object GameRunnerActor {
  val props = Props[GameRunnerActor]
  case class Initialize(gameName: GameOptions)
}

case class GameOptions(
  playerCount: Integer,
  gameName: String
)
