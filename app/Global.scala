import play.api._

import actors.PingActor
import akka.util.Timeout
import akka.actor.ActorSystem
import akka.pattern.ask

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")
    val system = ActorSystem("MyActorSystem")
    val pingActor = system.actorOf(PingActor.props, "pingActor")

    system.scheduler.schedule(Duration.Zero, 5 seconds, pingActor, PingActor.Initialize)
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

}
