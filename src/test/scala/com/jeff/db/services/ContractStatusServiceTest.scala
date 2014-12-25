/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: ContractStatusServiceTest.scala
 * created at: 下午6:27:56
 */
package com.jeff.db.services

import scala.slick.driver.MySQLDriver.simple.queryToInsertInvoker

import org.junit.Test

import com.jeff.db.DBConnection
import com.jeff.db.SlickDBDriver
import com.jeff.entities.Query.Expression
import com.jeff.entities.Query.Filter
import com.jeff.entities.Query.Order
import com.jeff.entities.Query.Sort
import com.jeff.entities.Tables
import com.jeff.services.ContractStatusService

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月29日
 * @version: $Rev$
 */
class ContractStatusServiceTest {

  def db = new DBConnection(SlickDBDriver.getDriver).dbObject

  val service = new ContractStatusService {}

  @Test
  def testOrder() {
    val res = service.search(Some(0), Some(10), Some(Array[Sort](Sort("id", Order.ASC), Sort("month", Order.DESC))), Some(Array[Filter](Filter("month", "一月", Some(Expression.EQ)))))
    println(res.size)
  }

  @Test
  def testCountProxy() {
    val filter = Array[Filter](Filter("exportContractNum", "11", Some(Expression.LIKE)), Filter("customer", "张三", Some(Expression.EQ)))
    val count = service.count(Some(filter))
    println(count)
  }

  @Test
  def testSearchEq() {
    val res = service.search(Some(0), Some(10), Some(Array[Sort]()), Some(Array[Filter](Filter("customer", "张三", Some(Expression.EQ)))))
    println(res.size)
  }

  @Test
  def testSearchLike() {
    val filters = Array[Filter](Filter("customer", "app1", Some(Expression.LIKE)), Filter("contractStatus", "1", Some(Expression.EQ)))
    val res = service.search(Some(0), Some(10), Some(Array[Sort]()), Some(filters))
    println(res.size)
  }

  @Test
  def testSearchSortNull() {
    val filters = Array[Filter](Filter("customer", "app1", Some(Expression.LIKE)), Filter("contractStatus", "1", Some(Expression.EQ)))
    val res = service.search(Some(0), Some(2), None, None)
    println(res.size)
  }

  @Test
  def testSearchPage() {
    val filters = Array[Filter](Filter("customer", "THE FEDERAL GROUP", Some(Expression.LIKE)))
    val res = service.search(Some(0), Some(3), Some(Array[Sort]()), Some(filters))
    println(res.size)
  }

  @Test
  def testSaveProxy() {
    val contract = Tables.ExportContractProgressRow(5, Option("一月"), Option("2013-11-13"), Some("MSC-USA-13E01"), Some("进行中"), Some("张三"), Some("美国"), Some("A"), Some("13.09"))
    val res = db.withSession { implicit session =>
      Tables.ExportContractProgress.insertOrUpdate(contract)
    }
    println(res)
  }

  @Test
  def testDeleteProxy() {
    val res = service.delete(5)
    println(res)
  }

  @Test
  def testUpdateProxy() {
    val contract = Tables.ExportContractProgressRow(5, Option("二月"), Option("2013-11-13"), Option("MSC-USA-13E01"))
    service.update(contract)
  }

  @Test
  def testExport() {
    service.export()
  }

}