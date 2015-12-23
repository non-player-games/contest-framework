import play.api._

import actors.GameRunnerActor
import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")
    val system = ActorSystem("BackgroundGameActors")
    val gameRunner = system.actorOf(GameRunnerActor.props, "gameRunner")

    system.scheduler.schedule(Duration.Zero, 10 seconds, gameRunner, GameRunnerActor.Initialize("Rock paper scissor"))
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

}
