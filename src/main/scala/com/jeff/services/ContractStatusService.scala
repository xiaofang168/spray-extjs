package com.jeff.services

import com.jeff.entities.Tables
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * 合同进程
 * Created by fangj on 2014/11/21.
 */
trait ContractStatusService extends BaseService {

  def getById(id: Int) = {
    db.withSession { implicit session =>
      Tables.Proxy.filter(_.id === id).first
    }
  }

  def update(proxy: Tables.ProxyRow) = {
    db.withSession { implicit session =>
      Tables.Proxy.update(proxy)
    }
  }

  def delete(id: Int) = {
    db.withSession { implicit session =>
      Tables.Proxy.filter(_.id === id).delete
    }
  }

  def save(proxy: Tables.ProxyRow): Int = {
    db.withSession { implicit session =>
      Tables.Proxy.insert(proxy)
    }
  }

  def saveOrUpdate(proxy: Tables.ProxyRow): Int = {
    db.withSession { implicit session =>
      Tables.Proxy.insertOrUpdate(proxy)
    }
  }

  def all(): List[Tables.ProxyRow] = {
    db.withSession { implicit session =>
      Tables.Proxy.list
    }
  }

}