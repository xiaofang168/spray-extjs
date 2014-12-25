/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: ProxyService.scala
 * created at: 2014年12月25日
 */
package com.jeff.services

import scala.slick.driver.MySQLDriver.simple.columnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.intColumnType
import scala.slick.driver.MySQLDriver.simple.optionColumnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.productQueryToUpdateInvoker
import scala.slick.driver.MySQLDriver.simple.queryToAppliedQueryInvoker
import scala.slick.driver.MySQLDriver.simple.queryToDeleteInvoker
import scala.slick.driver.MySQLDriver.simple.queryToInsertInvoker
import scala.slick.driver.MySQLDriver.simple.repToQueryExecutor
import scala.slick.driver.MySQLDriver.simple.stringColumnType
import scala.slick.driver.MySQLDriver.simple.stringOptionColumnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.valueToConstColumn

import org.slf4j.LoggerFactory

import com.jeff.Constant
import com.jeff.entities.Query.Expression
import com.jeff.entities.Query.Filter
import com.jeff.entities.Query.Order
import com.jeff.entities.Query.Sort
import com.jeff.entities.Tables

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月25日 下午5:57:38
 * @version: $Rev$
 */
trait ProxyService extends BaseService {

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
  private def getFilterProxy(filter: Option[Array[Filter]]) = {

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

  /**
   * 排序
   */
  private def sort[X, Y](query: scala.slick.lifted.Query[com.jeff.entities.Tables.Proxy, Y, Seq], sort: Option[Array[Sort]]) = {

    val (id, idOrder) = sort match {
      case Some(sort) => {
        sort.find(_.property.equals("id")) match {
          case Some(sort) => (Some(sort.property), Some(sort.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (appName, appNameOrder) = sort match {
      case Some(sort) => {
        sort.find(_.property.equals("appName")) match {
          case Some(sort) => (Some(sort.property), Some(sort.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (ip, ipOrder) = sort match {
      case Some(sort) => {
        sort.find(_.property.equals("ip")) match {
          case Some(sort) => (Some(sort.property), Some(sort.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (isEnable, isEnableOrder) = sort match {
      case Some(sort) => {
        sort.find(_.property.equals("isEnable")) match {
          case Some(sort) => (Some(sort.property), Some(sort.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    query.sortedBy(id) {
      idOrder match {
        case Some(Order.ASC) => _.id.asc
        case Some(Order.DESC) => _.id.desc
        case _ => _.id.desc
      }
    }.sortedBy(appName) {
      appNameOrder match {
        case Some(Order.ASC) => _.appName.asc
        case Some(Order.DESC) => _.appName.desc
        case _ => _.appName.desc
      }
    }.sortedBy(ip) {
      ipOrder match {
        case Some(Order.ASC) => _.ip.asc
        case Some(Order.DESC) => _.ip.desc
        case _ => _.ip.desc
      }
    }.sortedBy(isEnable) {
      isEnableOrder match {
        case Some(Order.ASC) => _.isEnable.asc
        case Some(Order.DESC) => _.isEnable.desc
        case _ => _.isEnable.desc
      }
    }

  }

  def search(offset: Option[Int], limit: Option[Int], sort: Option[Array[Sort]], filter: Option[Array[Filter]]) = {
    implicit class toQuery(query: scala.slick.lifted.Query[com.jeff.entities.Tables.Proxy, com.jeff.entities.Tables.Proxy#TableElementType, Seq]) {
      def order(sort: Option[Array[Sort]]) = new ProxyService {}.sort(query, sort)
    }
    db.withSession { implicit session =>
      getFilterProxy(filter).query.drop(offset.getOrElse(0)).take(limit.getOrElse(Constant.PAGESIZE)).order(sort).query.list
    }
  }

  def search(offset: Option[Int], limit: Option[Int], filter: Option[Array[Filter]]) = {
    db.withSession { implicit session =>
      getFilterProxy(filter).query.drop(offset.getOrElse(0)).take(limit.getOrElse(Constant.PAGESIZE)).list
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