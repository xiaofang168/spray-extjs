/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: Test.scala
 * created at: 2014年12月10日
 */
package slick

import com.jeff.db.DBConnection
import com.jeff.db.SlickDBDriver
import scala.slick.driver.MySQLDriver
import scala.slick.jdbc.meta.MTable
import scala.slick.jdbc.meta.MQName

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月10日 下午5:25:16
 * @version: $Rev$
 */
object Test extends App {
  import scala.slick.jdbc.meta.createModel
  import scala.slick.codegen.SourceCodeGenerator
  def db = new DBConnection(SlickDBDriver.getDriver).dbObject
  // fetch data model
  val driver = SlickDBDriver.getDriver
  val model = db.withSession { implicit session =>
    {
      val filteredTables = driver.getTables.list.filter((t: MTable) => {
        val schemaAndTableName = t.name.schema.getOrElse("") + "." + t.name.name
        List("test").map(e => if (!e.contains(".")) "." + e else e).contains(schemaAndTableName)
      })
      createModel(filteredTables, SlickDBDriver.getDriver) // you can filter specific tables here
    }
  }
  // customize code generator
  val codegen = new SourceCodeGenerator(model) {
    // override mapped table and class name
    override def entityName =
      dbTableName => dbTableName.split("_").map(_.capitalize).mkString("")
    override def tableName =
      dbTableName => dbTableName.toLowerCase.toCamelCase
  }
  val slickDriver = "scala.slick.driver.MySQLDriver"
  val outputFolder = "E:/workspaces/home/spray-extjs/src/main/scala"
  val pkg = "com.jeff.entities"

  codegen.writeToFile(
    slickDriver, outputFolder, pkg, "Tables", "Tables.scala")
}