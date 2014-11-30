package com.jeff.actors

import akka.actor.Actor
import com.jeff.actions.ContractStatusAction
import com.jeff.services.ContractStatusService

/**
 * Created by fangj on 2014/11/21.
 */
class ContractStatusActor extends Actor with ContractStatusService {

  def receive = {
    case ContractStatusAction.Get(id) => sender ! getById(id)
    case ContractStatusAction.Update() => sender ! update()
    case ContractStatusAction.Delete(id) => sender ! delete()
    case ContractStatusAction.Save() => sender ! save()
    case ContractStatusAction.All => sender ! all()
  }

}