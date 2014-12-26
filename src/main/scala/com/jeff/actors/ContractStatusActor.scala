package com.jeff.actors

import com.jeff.actions.ContractStatusAction
import com.jeff.entities.ResponseSearch
import com.jeff.services.ContractStatusService

import akka.actor.Actor
import akka.actor.actorRef2Scala

/**
 * Created by fangj on 2014/11/21.
 */
class ContractStatusActor extends Actor with ContractStatusService {

  def receive = {
    case ContractStatusAction.Get(id) => sender ! getById(id)
    case ContractStatusAction.Update(id, contract) => sender ! update(contract)
    case ContractStatusAction.Delete(id) => sender ! delete(id)
    case ContractStatusAction.Deletes(ids) => sender ! delete(ids)
    case ContractStatusAction.Save(contract) => sender ! save(contract)
    case ContractStatusAction.All(searchObj) => {
      val total = count(searchObj.filter)
      val list = search(searchObj.offset, searchObj.limit, searchObj.sort, searchObj.filter)
      sender ! ResponseSearch("000", total, list)
    }
  }

}