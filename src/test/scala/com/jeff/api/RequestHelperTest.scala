/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: RequestHelperTest.scala
 * created at: 2014年12月20日
 */
package com.jeff.api

import org.junit.Test
import com.jeff.entities.Query
import org.junit.Assert

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月20日 上午11:23:25
 * @version: $Rev$
 */
class RequestHelperTest {

  @Test
  def testGetSearch() {
    val $sort = """[{"property":"id","direction":"DESC"}]"""
    val $filter = """[{"property":"id","value":1,"expression":"$eq"},{"property":"$or","value":[{"property":"age","value":10,"expression":"$lt"},{"property":"age","value":20,"expression":"$gt"}]}]"""
    val params = Map("$page" -> "1", "$offset" -> "10", "$limit" -> "5", "$sort" -> $sort, "$filter" -> $filter)
    val search = RequestHelper.getSearch(params)
    println(search)
    Assert.assertEquals(Some(5), search.limit)
  }

  @Test
  def testE() {
    println(Query.Order.DESC.toString())
  }

}