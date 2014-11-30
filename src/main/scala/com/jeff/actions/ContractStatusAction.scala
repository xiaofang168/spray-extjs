package com.jeff.actions

import com.jeff.api.RequestMessage

/**
 * Created by fangj on 2014/11/21.
 */
object ContractStatusAction extends CommonAction {

  case class Update() extends RequestMessage

  case class Save() extends RequestMessage


}
