/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: BaseService.scala
 * created at: 下午8:45:06
 */
package com.jeff.services

import com.jeff.db.DBConnection
import com.jeff.db.SlickDBDriver
import scala.slick.lifted.TableQuery
import com.jeff.entities.Tables

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月29日
 * @version: $Rev$
 */
private[services] trait BaseService {
  def db = new DBConnection(SlickDBDriver.getDriver).dbObject
}