package com.jeff.actions

import com.jeff.api.RequestMessage
import com.jeff.entities.Tables


/**
 * Created by fangj on 2014/11/21.
 */
object ContractStatusAction extends CommonAction {

  case class Update(id: Int, proxy: Tables.ProxyRow) extends RequestMessage

  case class Save(proxy: Tables.ProxyRow) extends RequestMessage
  
  case object Export extends RequestMessage

}
