/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: Query.scala
 * created at: 2014年12月17日
 */
package com.jeff.entities

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月17日 下午12:24:28
 * @version: $Rev$
 */

object Query extends {

} with Query

trait Query {
  case class Sort(property: String, direction: Order.Value)
  case class Filter(property: String, value: Any, expression: Option[Expression.Value])
  object Order extends Enumeration {
    val ASC, DESC = Value
  }
  object Expression extends Enumeration {
    val EQ, /**不等于*/ NEQ, /**小于*/ LT, /**小于或等于*/ LE, /**大于*/ GT, /**大于或等于*/ GE, /**in*/ IN, /**not in*/ NIN, /**between*/ BETWEEN, /**null*/ NULL, /**like*/ LIKE, /**or*/ OR, /**and*/ AND = Value
  }
}

