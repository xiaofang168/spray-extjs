package com.jeff.actions

import com.jeff.api.RequestMessage

import com.jeff.entities.Query._

/**
 * Created by fangj on 2014/11/21.
 */
private[actions] trait CommonAction {

  case class Delete(id: Int) extends RequestMessage

  case class Get(id: Int) extends RequestMessage

  case object All extends RequestMessage

  case class Search($offset: Option[Int] = Some(0), $limit: Option[Int] = Some(20), $sort: Option[Array[Sort]] = Some(Array[Sort](Sort("id", Order.DESC))), $filter: Option[Filter])

}
