package actors

import akka.actor.{Actor, ActorLogging, Props}

class PingActor extends Actor with ActorLogging {
  import PingActor._

  var counter = 0
  val pongActor = context.actorOf(PongActor.props, "pongActor")

  def receive = {
    case Initialize =>
      log.info("In PingActor - starting ping-pong")
      pongActor ! PingMessage("ping")
    case PongActor.PongMessage(text) =>
      log.info("In PingActor - received message: {}", text)
      counter += 1
      // need to fix the below so that scheduler can recursive make call
      if (counter == 42) context.stop(self)
      else sender() ! PingMessage("ping")
  }
}

object PingActor {
  val props = Props[PingActor]
  case object Initialize
  case class PingMessage(text: String)
}
