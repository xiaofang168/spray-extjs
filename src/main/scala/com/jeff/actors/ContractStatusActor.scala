package com.jeff.actors

import akka.actor.Actor
import com.jeff.actions.ContractStatusAction
import com.jeff.services.ContractStatusService
import com.jeff.entities.Tables
import com.jeff.entities.Query._
import com.jeff.entities.ResponseSearch

/**
 * Created by fangj on 2014/11/21.
 */
class ContractStatusActor extends Actor with ContractStatusService {

  def receive = {
    case ContractStatusAction.Get(id) => sender ! getById(id)
    case ContractStatusAction.Update(id, proxy) => sender ! update(proxy)
    case ContractStatusAction.Delete(id) => sender ! delete(id)
    case ContractStatusAction.Save(proxy) => sender ! save(proxy)
    case ContractStatusAction.All(searchObj) => {
      val total = count(searchObj.filter)
      val list = search(searchObj.offset, searchObj.limit, searchObj.sort, searchObj.filter)
      sender ! ResponseSearch("000", total, list)
    }
  }

}