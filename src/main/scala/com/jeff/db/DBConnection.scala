package com.jeff.db

import scala.slick.driver.JdbcProfile
import com.typesafe.config.ConfigFactory

/**
 * Created by fangj on 2014/11/22.
 */
class DBConnection(override val profile: JdbcProfile) extends Profile {
  import profile.simple._
  def dbObject(): Database = {
    val env = scala.util.Properties.envOrElse("runMode", "dev")
    val config = ConfigFactory.load(env)
    val url = config.getString("db.url")
    val username = config.getString("db.username")
    val password = config.getString("db.password")
    val driver = config.getString("db.driver")
    println("Connection info =>" + "Run mode: " + env + ", db url: " + url + ", driver: " + driver)
    Database.forURL(url, username, password, null, driver)
  }
}
