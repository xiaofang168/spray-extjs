/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: ProxyActor.scala
 * created at: 2014年12月25日
 */
package com.jeff.actors

import com.jeff.actions.ProxyAction
import com.jeff.entities.ResponseSearch
import com.jeff.services.ProxyService

import akka.actor.Actor
import akka.actor.actorRef2Scala

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月25日 下午5:55:33
 * @version: $Rev$
 */
class ProxyActor extends Actor with ProxyService {

  def receive = {
    case ProxyAction.Get(id) => sender ! getById(id)
    case ProxyAction.Update(id, proxy) => sender ! update(proxy)
    case ProxyAction.Delete(id) => sender ! delete(id)
    case ProxyAction.Save(proxy) => sender ! save(proxy)
    case ProxyAction.All(searchObj) => {
      val total = count(searchObj.filter)
      val list = search(searchObj.offset, searchObj.limit, searchObj.sort, searchObj.filter)
      sender ! ResponseSearch("000", total, list)
    }
  }

}