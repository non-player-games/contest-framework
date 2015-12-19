package controllers

import javax.inject.Inject

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import play.api._
import play.api.libs.concurrent.Promise
import play.api.libs.iteratee.{Concurrent, Enumerator, Iteratee}
import play.api.libs.ws._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(ws: WSClient) extends Controller {
  // endpoint that opens an echo websocket
  def wsEcho = WebSocket.using[String] {
    request => {
      Logger.info(s"wsEcho, client connected.")
      var channel: Option[Concurrent.Channel[String]] = None
      val outEnumerator: Enumerator[String] = Concurrent.unicast(c => channel = Some(c))

      val inIteratee: Iteratee[String, Unit] = Iteratee.foreach[String](receivedString => {
        // send string back
        Logger.info(s"wsEcho, received: $receivedString")
        channel.foreach(_.push(receivedString))
      })

      (inIteratee, outEnumerator)
    }
  }

  // sends the time every second, ignores any input
  def wsTime = WebSocket.using[String] {
    request =>
      Logger.info(s"wsTime, client connected.")

      val outEnumerator: Enumerator[String] = Enumerator.repeatM(Promise.timeout(s"${new java.util.Date()}", 1000))
      val inIteratee: Iteratee[String, Unit] = Iteratee.ignore[String]

      (inIteratee, outEnumerator)
  }

  // sends ping pong every second
  def wsPingPong = WebSocket.using[String] {
    request =>
      Logger.info(s"wsPingPong, client connected.")

      var switch: Boolean = true
      val outEnumerator = Enumerator.repeatM[String](Promise.timeout({
        switch = !switch
        if (switch) "                <----- pong" else "ping ----->"
      }, 1000))

      (Iteratee.ignore[String], outEnumerator)
  }
}
