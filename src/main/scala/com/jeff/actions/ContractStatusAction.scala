package com.jeff.actions

import com.jeff.api.RequestMessage
import com.jeff.entities.Tables


/**
 * Created by fangj on 2014/11/21.
 */
object ContractStatusAction extends CommonAction {

  case class Update(id: Int, contract: Tables.ExportContractProgressRow) extends RequestMessage

  case class Save(contract: Tables.ExportContractProgressRow) extends RequestMessage

}
