/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: dbproxy-sp
 * Description: WebStart.scala
 * created at: 2014年11月25日
 */
package com.jeff

import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.server.bio.SocketConnector
import org.eclipse.jetty.server.Server
import spray.servlet.Servlet30ConnectorServlet
import spray.servlet.Initializer
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月25日 下午2:30:28
 * @version: $Rev$
 */
object WebStart {

  val logger = LoggerFactory.getLogger(this.getClass())

  def main(args: Array[String]) {
    start
  }

  def start() {
    val server = new Server(Constant.port)
    val rootPath = this.getClass().getClassLoader().getResource("").getPath()
    val path = new File(rootPath).getPath()
    val webPath = if (path.lastIndexOf("\\") == -1) path.substring(0,path.lastIndexOf("/")) else path.substring(0,path.lastIndexOf("\\"))
    logger.info("web路径："+s"$webPath\\webapp")
    val webapp = new WebAppContext(s"$webPath\\webapp", Constant.webContext)
    server.setHandler(webapp)
    server.start()
    server.join()
  }
  
}