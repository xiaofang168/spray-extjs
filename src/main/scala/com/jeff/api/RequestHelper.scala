/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: Request.scala
 * created at: 2014年12月20日
 */
package com.jeff.api

import com.jeff.entities.Query._
import com.jeff.Constant

/**
 * 请求帮助类
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月20日 上午11:13:04
 * @version: $Rev$
 */
object RequestHelper {

  def getSearch(params: Map[String, String]): Search = {
    import spray.json.JsonParser
    import JsonImplicits._

    val page = params.getOrElse(RequestParams.page, "1")
    val offset = params.getOrElse(RequestParams.offset, "0")
    val limit = params.getOrElse(RequestParams.limit, Constant.PAGESIZE.toString)
    val sort = params.get(RequestParams.sort) match {
      case Some(s) => Some(JsonParser(s).convertTo[Array[Sort]])
      case None => None
    }
    val filter = params.get(RequestParams.filter) match {
      case Some(f) => Some(JsonParser(f).convertTo[Array[Filter]])
      case None => None
    }
    Search(Some(offset.toInt), Some(limit.toInt), sort, filter)
  }

}