package com.jeff.api

import akka.actor.{ Actor, Props }
import com.jeff.actions.ContractStatusAction
import com.jeff.actors.ContractStatusActor
import spray.routing._
import spray.http._
import com.jeff.entities.Tables

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

  import com.jeff.api.JsonImplicits._
  import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller

  // 合同进程路由定义
  val contractStatusRoute = path("proxy") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        parameterMap { conditions =>
          handleContractStatusRequest(ContractStatusAction.All)
        }
      }
    } ~
      post {
        respondWithMediaType(MediaTypes.`application/json`) {
          entity(as[Tables.ProxyRow]) { proxy =>
            handleContractStatusRequest(ContractStatusAction.Save(proxy))
          }
        }
      }
  } ~
    path("proxy" / Segment) { (id) =>
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          handleContractStatusRequest(ContractStatusAction.Get(id.toInt))
        }
      } ~
        put {
          respondWithMediaType(MediaTypes.`application/json`) {
            entity(as[Tables.ProxyRow]) { proxy =>
              handleContractStatusRequest(ContractStatusAction.Update(id.toInt, proxy))
            }
          }
        } ~
        delete {
          respondWithMediaType(MediaTypes.`application/json`) {
            handleContractStatusRequest(ContractStatusAction.Delete(id.toInt))
          }
        }
    }

  def handleContractStatusRequest(requsetMessage: RequestMessage): Route

}