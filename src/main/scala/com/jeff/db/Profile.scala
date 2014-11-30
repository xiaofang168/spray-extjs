package com.jeff.db

import scala.slick.driver.JdbcProfile

/**
 * Created by fangj on 2014/11/22.
 */
//define driver
trait Profile {
  val profile: JdbcProfile
}
