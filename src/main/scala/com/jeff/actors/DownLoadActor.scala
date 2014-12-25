/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: CommonActor.scala
 * created at: 2014年12月24日
 */
package com.jeff.actors

import akka.actor.Actor
import com.jeff.actions.CommonAction
import com.jeff.actions.ContractStatusAction
import com.jeff.services.ContractStatusService

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月24日 下午6:23:30
 * @version: $Rev$
 */
class DownLoadActor extends Actor {
  def receive = {
    case ContractStatusAction.Export => sender ! new ContractStatusService {}.export
  }
}