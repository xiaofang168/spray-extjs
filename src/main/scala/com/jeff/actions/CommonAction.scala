package com.jeff.actions

import com.jeff.api.RequestMessage

import com.jeff.entities.Query._

/**
 * Created by fangj on 2014/11/21.
 */
private[actions] trait CommonAction {

  case class Delete(id: Int) extends RequestMessage
  
  case class Deletes(ids: Array[Int]) extends RequestMessage

  case class Get(id: Int) extends RequestMessage

  case class All(search: Search) extends RequestMessage

  case object Export extends RequestMessage

}
