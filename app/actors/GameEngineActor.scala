package actors

import akka.actor.{Props, ActorLogging, Actor}

/**
  * Game runner actor to deliver game life cycle as below:
  * 1. GameSetup
  * 2. GameStart
  * 3. TurnStart
  * 4. TurnFinish
  * 5. GameFinish
  */
class GameEngineActor extends Actor with ActorLogging {
  import GameEngineActor._

  var turn: Integer = 0
  var gameName: String = ""

  override def receive = {
    case GameSetup(gameName: String) => {
      this.gameName = gameName

      log.info(s"Setting up game $gameName")
    }
    case GameStart => {
      log.info("Starting game")
      sender() ! TurnStart("game state sample")
    }
    case TurnFinish(botMoves: Map[String, String]) => {
      turn += 1
      log.info(s"Processing turn $turn with bot moves $botMoves")
      // process turn
      // if game is ended, send sender back GameFinish

      if (turn == 10) {
        // game end, clean up and tell game runner we are done
        turn = 0
        gameName = ""
        sender() ! GameFinish
      } else {
        sender() ! TurnStart(s"game state sample for turn $turn")
      }
    }
  }
}

object GameEngineActor {
  val props = Props[GameEngineActor]
  case class GameSetup(gameName: String)
  case object GameStart
  case class TurnStart(gameState: String) // probably pass game state as json later
  // TODO: maybe consider about sending game state as a map considering sometimes
  // each bot has different visible game state
  case class TurnFinish(botMoves: Map[String, String])
  case object GameFinish
}
