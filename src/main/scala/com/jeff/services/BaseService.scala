/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: BaseService.scala
 * created at: 下午8:45:06
 */
package com.jeff.services

import com.jeff.db.DBConnection
import com.jeff.db.SlickDBDriver
import scala.slick.lifted.Column

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月29日
 * @version: $Rev$
 */
case class MaybeFilter[X, Y](val query: scala.slick.lifted.Query[X, Y, Seq]) {
  def filteredBy(op: Option[_])(f: (X) => Column[Option[Boolean]]) = {
    op map { o => MaybeFilter(query.filter(f)) } getOrElse { this }
  }
}

private[services] trait BaseService {
  implicit def maybeFilterConversor[X, Y](q: scala.slick.lifted.Query[X, Y, Seq]) = new MaybeFilter(q)
  def db = new DBConnection(SlickDBDriver.getDriver).dbObject
}