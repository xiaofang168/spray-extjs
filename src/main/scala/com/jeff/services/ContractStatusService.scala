package com.jeff.services

import com.jeff.entities.Tables
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * 合同进程
 * Created by fangj on 2014/11/21.
 */
trait ContractStatusService extends BaseService {

  def getById(id: Long) = {

  }

  def update() = {

  }

  def delete() = {

  }

  def save() = "test"

  def all(): List[Tables.ProxyRow] = {
    db.withSession { implicit session =>
      Tables.Proxy.list
    }
  }

}