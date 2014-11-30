package com.jeff.db

import scala.slick.driver.JdbcProfile

import org.junit.Assert

import org.junit.Test

/**
 * Created by fangj on 2014/11/22.
 */

class SlickDemoAppTest {

  @Test
  def testDbConnection() {
    (new SlickDemo).test
  }

}

class SlickDemo(override val profile: JdbcProfile = SlickDBDriver.getDriver) extends Profile {
  import profile.simple._
  val conn = new DBConnection(profile)
  def test: Unit = {
    conn.dbObject withSession { implicit session: Session =>
      Assert.assertNotNull(session)
    }
  }
}


