/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: JsonImplicits.scala
 * created at: 2014年12月15日
 */
package com.jeff.api

import spray.json.DefaultJsonProtocol
import spray.json.JsObject
import spray.json.JsValue
import spray.json.RootJsonFormat
import spray.json.pimpAny
import com.jeff.entities.Tables

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

}