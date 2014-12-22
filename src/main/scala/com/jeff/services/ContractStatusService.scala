package com.jeff.services

import com.jeff.entities.Tables
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._
import com.jeff.entities.Query._
import scala.slick.lifted.CanBeQueryCondition
import scala.slick.lifted.Column
import org.slf4j.LoggerFactory

/**
 * 合同进程
 * Created by fangj on 2014/11/21.
 */
trait ContractStatusService extends BaseService {

  def logger = LoggerFactory.getLogger(classOf[ContractStatusService])

  def getById(id: Int) = {
    db.withSession { implicit session =>
      Tables.Proxy.filter(_.id === id).first
    }
  }

  def find(id: Option[Int], appName: Option[String], ip: Option[String], isEnable: Option[String]) = {
    db.withSession { implicit session =>
      Tables.Proxy.filteredBy(id) { _.id === id }
        .filteredBy(appName) { _.appName like appName.map("%" + _ + "%").getOrElse("") }
        .query.list
    }
  }

  def count(filter: Option[Array[Filter]]): Int = {
    db.withSession { implicit session =>
      getFilterProxy(filter).query.length.run
    }
  }

  /**
   * 获取过滤后的Proxy
   */
  def getFilterProxy(filter: Option[Array[Filter]]) = {

    val (id, idExpression): (Option[Int], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("id")) match {
          case Some(filter) => (Some(filter.value.toString.toInt), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (appName, appNameExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("appName")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (ip, ipExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("ip")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (isEnable, isEnableExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("isEnable")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    Tables.Proxy.filteredBy(id) { _.id === id }
      .filteredBy(appName) {
        appNameExpression match {
          case Some(Expression.EQ) => _.appName === appName
          case Some(Expression.LIKE) => _.appName like appName.map("%" + _ + "%").getOrElse("")
          case _ => _.appName === appName
        }
      }.filteredBy(ip) {
        ipExpression match {
          case Some(Expression.EQ) => _.ip === ip
          case Some(Expression.LIKE) => _.ip like ip.map("%" + _ + "%").getOrElse("")
          case _ => _.appName === ip
        }
      }.filteredBy(isEnable)(_.isEnable === isEnable)

  }

  def search(offset: Option[Int], limit: Option[Int], sort: Option[Array[Sort]], filter: Option[Array[Filter]]) = {
    db.withSession { implicit session =>
      getFilterProxy(filter).query.drop(offset.getOrElse(0)).take(limit.getOrElse(5)).list
    }
  }

  def update(proxy: Tables.ProxyRow) = {
    db.withSession { implicit session =>
      Tables.Proxy.filter(_.id === proxy.id).update(proxy)
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