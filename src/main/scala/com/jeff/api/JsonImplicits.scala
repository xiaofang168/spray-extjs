/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: JsonImplicits.scala
 * created at: 2014年12月15日
 */
package com.jeff.api

import spray.json.DefaultJsonProtocol
import com.jeff.entities.Tables
import com.jeff.entities.Query

/**
 * json 对象 隐式转换 处理
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月7日 下午6:27:53
 * @version: $Rev: 3982 $
 */
object JsonImplicits extends DefaultJsonProtocol {
  import spray.json._
  import DefaultJsonProtocol._

  implicit val impProxyRow = jsonFormat4(Tables.ProxyRow)
  implicit val impExportContractProgressRow = jsonFormat20(Tables.ExportContractProgressRow)

  implicit object sortJsonFormat extends RootJsonFormat[Array[Query.Sort]] {

    def write(sort: Array[Query.Sort]) = JsObject()

    def read(value: JsValue) = {
      value match {
        case JsArray(elements) => {
          elements.map {
            e =>
              {
                val map = e.convertTo[Map[String, String]]
                val direction = map.get("direction") match {
                  case Some(e) => if ("ASC".equals(e.toUpperCase())) Query.Order.ASC else Query.Order.DESC
                  case None => deserializationError("Sort expected")
                }
                Query.Sort(map("property"), direction)
              }
          } toArray
        }
        case _ => deserializationError("Sort expected")
      }
    }
  }

  implicit object filterJsonFormat extends RootJsonFormat[Array[Query.Filter]] {

    def write(filter: Array[Query.Filter]) = JsObject()

    def read(value: JsValue) = value match {

      case JsArray(elements) => {
        getFilters(elements.asInstanceOf[List[JsObject]])
      }

      case _ => deserializationError("Filter expected")

    }
  }

  private def getFilters(elements: List[JsObject]): Array[Query.Filter] = {

    elements.map { e =>
      {
        val map = e.convertTo[Map[String, JsValue]]

        val property = map("property") match {
          case JsString(value) => value
          case _ => deserializationError("Filter expected")
        }

        val value = map("value") match {
          case JsString(value) => value
          case JsNumber(value) => value
          case JsArray(value) => value
          case _ => deserializationError("Filter expected")
        }

        val expression = map.get("expression") match {
          case Some(JsString(e)) => e.toString
          case _ => "$eq"
        }
        getFilter(property, value, expression)
      }

    } toArray

  }

  /**获取单个过滤对象*/
  private def getFilter(property: String, value: Any, expression: String): Query.Filter = property match {
    case "$or" | "$and" => {
      val filters = getFilters(value.asInstanceOf[List[JsObject]])
      new Query.Filter(property, filters, Some(Query.Expression.LIKE))
    }
    case _ =>
      expression match {
        case "$eq" => new Query.Filter(property, value, Some(Query.Expression.EQ))
        case "$neq" => new Query.Filter(property, value, Some(Query.Expression.NEQ))
        case "$lt" => new Query.Filter(property, value, Some(Query.Expression.LT))
        case "$le" => new Query.Filter(property, value, Some(Query.Expression.LE))
        case "$gt" => new Query.Filter(property, value, Some(Query.Expression.GT))
        case "$ge" => new Query.Filter(property, value, Some(Query.Expression.GE))
        case "$in" => new Query.Filter(property, value, Some(Query.Expression.IN))
        case "$nin" => new Query.Filter(property, value, Some(Query.Expression.NIN))
        case "$between" => new Query.Filter(property, value, Some(Query.Expression.BETWEEN))
        case "$null" => new Query.Filter(property, value, Some(Query.Expression.NULL))
        case "$like" => new Query.Filter(property, value, Some(Query.Expression.LIKE))
        case _ => deserializationError("Filter expected")
      }
  }

}