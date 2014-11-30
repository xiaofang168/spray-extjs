package com.jeff.api

import akka.actor.{OneForOneStrategy, ActorRef, Props, Actor}
import com.jeff.api.CustomerRequest.WithProps
import akka.actor.SupervisorStrategy.Stop
import scala.concurrent.duration._
import spray.httpx.Json4sSupport
import spray.routing.RequestContext
import org.json4s.DefaultFormats

/**
 * Created by fangj on 2014/11/21.
 */
trait CustomerRequest extends Actor with Json4sSupport {

  import context._

  def requestContext: RequestContext

  def target: ActorRef

  def requestMessage: RequestMessage

  setReceiveTimeout(5.seconds)

  target ! requestMessage

  def receive = {
    case s: String => complete(s)
  }


  def complete[T <: AnyRef](obj: T) = {
    requestContext.complete(obj)
    stop(self)
  }

  override val supervisorStrategy =
    OneForOneStrategy() {
      case e: Exception => {
        complete("error")
        Stop
      }
    }


}

object CustomerRequest {

  case class WithProps(requestContext: RequestContext, props: Props, requestMessage: RequestMessage) extends CustomerRequest {
    lazy val target = context.actorOf(props)

    implicit def json4sFormats = DefaultFormats
  }

}

trait CustomerRequestCreator {
  this: Actor =>
  def customerRequest(requestContext: RequestContext, props: Props, requestMessage: RequestMessage) =
    context.actorOf(Props(new WithProps(requestContext, props, requestMessage)))
}
