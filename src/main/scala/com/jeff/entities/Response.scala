/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: Response.scala
 * created at: 下午9:58:13
 */
package com.jeff.entities

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月22日
 * @version: $Rev$
 */
trait Response

case class ResponseSearch(code: String, total: Int, results: Any) extends Response