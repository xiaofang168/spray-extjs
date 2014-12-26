/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: ExportContractProgressRoute.scala
 * created at: 2014年12月20日
 */
package com.jeff.api

import spray.routing.HttpService
import spray.http.MediaTypes
import com.jeff.actions.ContractStatusAction
import com.jeff.entities.Tables
import spray.routing.Route
import java.text.SimpleDateFormat
import java.util.Date
import spray.http.HttpHeaders

/**
 * 出口合同进程服务路由
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月20日 上午11:02:43
 * @version: $Rev$
 */
trait ContractServiceRoute extends HttpService {
  import com.jeff.api.JsonImplicits._
  import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller

  // 合同进程路由定义
  val contractStatusRoute = path("contract") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        parameterMap { params =>
          // 构造查询条件
          val search = RequestHelper.getSearch(params)
          handleContractStatusRequest(ContractStatusAction.All(search))
        }
      }
    } ~
      post {
        respondWithMediaType(MediaTypes.`application/json`) {
          entity(as[Tables.ExportContractProgressRow]) { contract =>
            handleContractStatusRequest(ContractStatusAction.Save(contract))
          }
        }
      }
  } ~
    path("contract" / "_export") {
      get {
        respondWithMediaType(MediaTypes.`application/excel`) {
          val dateFormatter = new SimpleDateFormat("yyyyMMdd")
          val dateStr = dateFormatter.format(new Date)
          val fileName = new String(s"出口合同进程表${dateStr}.xls".getBytes("GBK"), "ISO8859_1")
          respondWithHeader(HttpHeaders.`Content-Disposition`("attachment", Map(("filename", fileName)))) {
            handleDownContractRequest(ContractStatusAction.Export)
          }
        }
      }
    } ~
    path("contract" / Segment) { (id) =>
      val ids = id.split(",").map(_.toInt)
      delete {
        respondWithMediaType(MediaTypes.`application/json`) {
          handleContractStatusRequest(ContractStatusAction.Deletes(ids))
        }
      } ~
        get {
          respondWithMediaType(MediaTypes.`application/json`) {
            handleContractStatusRequest(ContractStatusAction.Get(ids(0)))
          }
        } ~
        put {
          respondWithMediaType(MediaTypes.`application/json`) {
            entity(as[Tables.ExportContractProgressRow]) { contract =>
              handleContractStatusRequest(ContractStatusAction.Update(ids(0), contract))
            }
          }
        }
    }

  def handleContractStatusRequest(requsetMessage: RequestMessage): Route

  def handleDownContractRequest(requsetMessage: RequestMessage): Route

}