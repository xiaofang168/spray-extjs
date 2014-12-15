/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: ContractStatusServiceTest.scala
 * created at: 下午6:27:56
 */
package com.jeff.db.services

import org.junit.Test
import com.jeff.db.DBConnection
import com.jeff.db.SlickDBDriver
import scala.slick.jdbc.{ StaticQuery => Q }
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.GetResult
import org.junit.Assert
import scala.slick.driver.JdbcProfile
import com.typesafe.config.ConfigFactory
import com.jeff.entities.Tables
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._
import com.jeff.services.BaseService
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
  def testAllWithSql() {
    val query = sql"select ID, NAME from test".as[(Int, String)]
    val tests = db.withSession { implicit session =>
      query.list
    }
    Assert.assertTrue(tests.size > 0)
  }

  def query() {

  }

  @Test
  def testAll() {
    val list: List[Tables.TestRow] = db.withSession { implicit session =>
      Tables.Test.list
    }
    val s = list.map(obj => { obj.id + ", " + obj.name.getOrElse("") }).mkString("\n")
    println(s)
  }

  @Test
  def testProxyAll() {
    val list: List[Tables.ProxyRow] = db.withSession { implicit session =>
      Tables.Proxy.list
    }
  }

  @Test
  def testGetProxy() {
    val id = 5
    val res = db.withSession { implicit session =>
      Tables.Proxy.filter(_.id === id).first
    }
    println(res)
  }

  @Test
  def testSaveProxy() {
    val proxy = Tables.ProxyRow(5, Option("aa"), Option("aa"), Option("aa"))
    val res = db.withSession { implicit session =>
      Tables.Proxy.insertOrUpdate(proxy)
    }
    println(res)
  }

  @Test
  def testDeleteProxy() {
    val res = service.delete(5)
    println(res)
  }

  @Test
  def testSaveWithSql() {
    db.withSession {
      implicit session =>
        {
          (Q.u + "insert into test (NAME) values ('aa')").execute
        }
    }
  }

  @Test
  def testUpdateWithSql() {
    db.withSession(implicit session => {
      sqlu"update test set NAME='bb' where id=4".first
    })
  }

  @Test
  def testDeleteWithSql() {
    db.withSession(implicit session => {
      sqlu"delete from test where id=4".first
    })
  }

}