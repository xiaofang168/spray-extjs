/*
 * Copyright 2014 The Jeff CO.Ltd
 * Prject: spray-extjs
 * Description: CodeGeneration.scala
 * created at: 下午7:23:30
 */
package slick
/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月29日
 * @version: $Rev$
 */
object CodeGeneration extends App {
  val slickDriver = "scala.slick.driver.MySQLDriver"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/test"
  val outputFolder = "E:/workspaces/home/spray-extjs/src/main/scala"
  val pkg = "com.jeff.entities"
  val user = "root"
  val password = "root"
  //scala.slick.codegen.SourceCodeGenerator.main(Array(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password))
  MySourceCodeGenerator.main(Array(slickDriver, jdbcDriver, url, outputFolder, pkg, "test", user, password,""))
}