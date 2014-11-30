package com.jeff.actions

import com.jeff.api.RequestMessage

/**
 * Created by fangj on 2014/11/21.
 */
private[actions] trait CommonAction {

  case class Delete(id:Long) extends  RequestMessage

  case class Get(id:Long) extends  RequestMessage

  case object All extends  RequestMessage

}
