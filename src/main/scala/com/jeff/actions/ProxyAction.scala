/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: ProxyAction.scala
 * created at: 2014年12月25日
 */
package com.jeff.actions

import com.jeff.entities.Tables
import com.jeff.api.RequestMessage

/**
 * 数据库代理action
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月25日 下午5:52:00
 * @version: $Rev$
 */
object ProxyAction extends CommonAction {
  
  case class Update(id: Int, proxy: Tables.ProxyRow) extends RequestMessage

  case class Save(proxy: Tables.ProxyRow) extends RequestMessage
  
}