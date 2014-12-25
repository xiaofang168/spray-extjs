/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: dbproxy-sp
 * Description: Constant.scala
 * created at: 2014年7月4日
 */
package com.jeff

import com.typesafe.config.ConfigFactory

/**
 * 服务常量定义
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月4日 上午9:04:14
 * @version: $Rev: 3935 $
 */
object Constant {

  val config = ConfigFactory.load()
  /**服务平台主机ip地址*/
  val host = config.getString("sp.host")
  /**服务平台主机端口号*/
  val port = config.getInt("sp.port")
  /**web上下文根*/
  val webContext = config.getString("webapp.context")
  /**请求超时时间*/
  val requestTimeout = config.getInt("sp.request-timeout")
  /**分页大小*/
  val PAGESIZE = config.getInt("sp.page-size")
  /**应用名称(连接服务访问名称),默认为dbproxy*/
  val appName: String = config.getString("sp.name")

}