package actors

import akka.actor.{Props, Actor, ActorLogging}

/**
  * Actor to handle bot communication
  */
class BotActor extends Actor with ActorLogging {
  import BotActor._
  var name: String = ""

  override def receive = {
    case BotStart(gameName: String, botName: String) => {
      this.name = botName
      log.info(s"Bot $name starting on game $gameName")
    }
    case TurnStart(gameState: String) => {
      log.info(s"Bot $name got game state $gameState")
      sender ! "random move"
    }
  }
}

object BotActor {
  val props = Props[BotActor]
  case class BotStart(gameName: String, botName: String)
  case class TurnStart(gameState: String)
  case class TurnFinish(botMove: String)
}
