package com.jeff.db

import scala.slick.driver.JdbcProfile
import scala.slick.driver.H2Driver
import scala.slick.driver.MySQLDriver
import scala.slick.driver.PostgresDriver

/**
 * Created by fangj on 2014/11/22.
 */
object SlickDBDriver {
  val TEST = "test"
  val DEV = "dev"
  val PROD = "prod"
  def getDriver: JdbcProfile = {
    scala.util.Properties.envOrElse("runMode", "dev") match {
      case TEST => H2Driver
      case DEV => MySQLDriver
      case PROD => PostgresDriver
      case _ => PostgresDriver
    }
  }
}