package com.jeff.api

import akka.actor.{ Actor, Props }
import com.jeff.actions.ContractStatusAction
import com.jeff.actors.ContractStatusActor
import spray.routing._
import spray.http._
import com.jeff.entities.Tables
import com.jeff.actors.DownLoadActor
import java.text.SimpleDateFormat
import java.util.Date
import com.jeff.actors.ProxyActor
import com.jeff.actions.ProxyAction

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService with ContractServiceRoute with CustomerRequestCreator with CustomerDownRequestCreator {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(proxyRoute ~ contractStatusRoute)

  def handleProxyRequest(requsetMessage: RequestMessage): Route =
    ctx => customerRequest(ctx, Props[ProxyActor], requsetMessage)

  def handleDownProxyRequest(requsetMessage: RequestMessage): Route =
    ctx => customerDownRequest(ctx, Props[DownLoadActor], requsetMessage)

  def handleContractStatusRequest(requsetMessage: RequestMessage): Route =
    ctx => customerRequest(ctx, Props[ContractStatusActor], requsetMessage)

  def handleDownContractRequest(requsetMessage: RequestMessage): Route =
    ctx => customerDownRequest(ctx, Props[DownLoadActor], requsetMessage)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  import com.jeff.api.JsonImplicits._
  import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller

  // 合同进程路由定义
  val proxyRoute = path("proxy") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        parameterMap { params =>
          // 构造查询条件
          val search = RequestHelper.getSearch(params)
          handleProxyRequest(ContractStatusAction.All(search))
        }
      }
    } ~
      post {
        respondWithMediaType(MediaTypes.`application/json`) {
          entity(as[Tables.ProxyRow]) { proxy =>
            handleProxyRequest(ProxyAction.Save(proxy))
          }
        }
      }
  } ~
    path("proxy" / "_export") {
      get {
        respondWithMediaType(MediaTypes.`application/excel`) {
          val dateFormatter = new SimpleDateFormat("yyyyMMdd")
          val dateStr = dateFormatter.format(new Date)
          val fileName = new String(s"出口合同进程表${dateStr}.xls".getBytes("GBK"), "ISO8859_1")
          respondWithHeader(HttpHeaders.`Content-Disposition`("attachment", Map(("filename", fileName)))) {
            handleDownProxyRequest(ProxyAction.Export)
          }
        }
      }
    } ~
    path("proxy" / Segment) { (id) =>
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          handleProxyRequest(ProxyAction.Get(id.toInt))
        }
      } ~
        put {
          respondWithMediaType(MediaTypes.`application/json`) {
            entity(as[Tables.ProxyRow]) { proxy =>
              handleProxyRequest(ProxyAction.Update(id.toInt, proxy))
            }
          }
        } ~
        delete {
          respondWithMediaType(MediaTypes.`application/json`) {
            handleProxyRequest(ProxyAction.Delete(id.toInt))
          }
        }
    }

  def handleProxyRequest(requsetMessage: RequestMessage): Route

  def handleDownProxyRequest(requsetMessage: RequestMessage): Route

}