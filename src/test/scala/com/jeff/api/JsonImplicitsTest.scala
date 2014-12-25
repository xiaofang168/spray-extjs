/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: JsonImplicitsTest.scala
 * created at: 2014年12月20日
 */
package com.jeff.api

import org.junit.Test
import com.jeff.entities.Query
import com.sun.org.apache.xalan.internal.xsltc.compiler.Sort

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月20日 下午12:25:10
 * @version: $Rev$
 */
class JsonImplicitsTest {
  import JsonImplicits._

  @Test
  def testJsonImpSort {
    import spray.json.JsonParser
    val sort = """[{"property":"id","direction":"DESC"},{"property":"id","direction":"DESC"}]"""
    val a = JsonParser(sort).convertTo[Array[Query.Sort]]
    println(a.length)
    a.foreach(println)
  }

  @Test
  def testFilterJsonFormat() {
    import spray.json.JsonParser
    val filter = """[{"property":"id","value":1,"expression":"$eq"},{"property":"$or","value":[{"property":"age","value":10,"expression":"$ge"},{"property":"age","value":20,"expression":"$gt"}]}]"""
    val a = JsonParser(filter).convertTo[Array[Query.Filter]]
    a.foreach(e => e.value match {
      case a: Array[_] => a.foreach(println(_))
      case _ => println(e)
    })
  }

}