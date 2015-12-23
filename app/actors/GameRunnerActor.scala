package actors

import akka.actor.{Props, ActorLogging, Actor}

/**
  * Top level supervisor actor to manage Game Engine and Bots
  */
class GameRunnerActor extends Actor with ActorLogging {
  import GameRunnerActor._
  import GameEngineActor._

  val gameRunnerActor = context.actorOf(GameEngineActor.props, "gameRunner")

  override def receive = {
    case Initialize(gameName) => {
      gameRunnerActor ! GameEngineActor.GameSetup(gameName)
      gameRunnerActor ! GameEngineActor.GameStart
    }
    case TurnStart => {
      log.info("Got turn start message. Sending message toward bots")
      // TODO: pass game state info to all bots and get their responses
      gameRunnerActor ! TurnFinish
    }
    case GameFinish => {
      log.info("Game is finished")
    }
  }
}

object GameRunnerActor {
  val props = Props[GameRunnerActor]
  case class Initialize(gameName: String)
}
