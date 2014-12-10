/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: spray-extjs
 * Description: MySourceCodeGenerator.scala
 * created at: 2014年12月10日
 */
package slick

import scala.slick.driver.JdbcProfile
import scala.slick.jdbc.meta.MTable

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月10日 下午6:30:48
 * @version: $Rev$
 */
object MySourceCodeGenerator {
  
  import scala.slick.jdbc.meta.createModel
  import scala.slick.codegen.SourceCodeGenerator
  import scala.reflect.runtime.currentMirror

  def main(args: Array[String]) = {

    args.toList match {
      case slickDriver :: jdbcDriver :: url :: outputFolder :: pkg :: tables :: tail if tail.size == 0 || tail.size == 2 => {
        val driver: JdbcProfile = {
          val module = currentMirror.staticModule(slickDriver)
          val reflectedModule = currentMirror.reflectModule(module)
          val driver = reflectedModule.instance.asInstanceOf[JdbcProfile]
          driver
        }

        val db = driver.simple.Database

        val model = (tail match {
          case user :: password :: Nil => db.forURL(url, driver = jdbcDriver, user = user, password = password)
          case Nil => db.forURL(url, driver = jdbcDriver)
          case _ => throw new Exception("This should never happen.")
        }).withSession { implicit session =>
          {
            val filteredTables = driver.getTables.list.filter((t: MTable) => {
              val schemaAndTableName = t.name.schema.getOrElse("") + "." + t.name.name
              tables.split(",").map(e => if (!e.contains(".")) "." + e else e).contains(schemaAndTableName)
            })
            createModel(filteredTables, driver) // you can filter specific tables here
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

        codegen.writeToFile(slickDriver, outputFolder, pkg, "Tables", "Tables.scala")
      }
      case _ => {
    	 println("""|Usage:
    			  	| SourceCodeGenerator.main(Array(slickDriver, jdbcDriver, url, outputFolder, pkg, tables))
					| SourceCodeGenerator.main(Array(slickDriver, jdbcDriver, url, outputFolder, pkg, tables, user, password))
					|
					| slickDriver: Fully qualified name of Slick driver class, e.g. "scala.slick.driver.H2Driver"
					|
					| jdbcDriver: Fully qualified name of jdbc driver class, e.g. "org.h2.Driver"
					|
					| url: jdbc url, e.g. "jdbc:postgresql://localhost/test"
					|
					| outputFolder: Place where the package folder structure should be put
					|
					| pkg: Scala package the generated code should be places in
					|          
					| tables: use "," split
					|         
					| user: database connection user name
					|
					| password: database connection password
					""".stripMargin)
      }
    }
  }
  
}