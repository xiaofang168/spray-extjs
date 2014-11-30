/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: dbproxy-sp
 * Description: WebBoot.scala
 * created at: 2014年7月4日
 */
package com.jeff

import akka.actor.ActorSystem
import akka.actor.Props
import spray.servlet.WebBoot
import com.jeff.api.MyServiceActor

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月4日 上午11:05:21
 * @version: $Rev: 3241 $
 */
class Boot extends WebBoot {
  
  // we need an ActorSystem to host our application in
  val system = ActorSystem("spray-servlet-webapp")

  // the service actor replies to incoming HttpRequests
  val serviceActor = system.actorOf(Props[MyServiceActor])

}