package com.jeff.api

import akka.actor.{ Actor, Props }
import com.jeff.actions.ContractStatusAction
import com.jeff.actors.ContractStatusActor

import spray.routing._
import spray.http._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService with CustomerRequestCreator {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(contractStatusRoute)

  def handleContractStatusRequest(requsetMessage: RequestMessage): Route =
    ctx => customerRequest(ctx, Props[ContractStatusActor], requsetMessage)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  // 合同进程路由定义
  val contractStatusRoute = path("contractstatus") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        parameterMap { conditions =>
          handleContractStatusRequest(ContractStatusAction.Save())
        }
      }
    }
  }

  def handleContractStatusRequest(requsetMessage: RequestMessage): Route

}