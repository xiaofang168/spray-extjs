package com.jeff.entities
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Test.ddl ++ Proxy.ddl

  /**
   * Entity class storing rows of table Test
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(50,true), Default(None)
   */
  case class TestRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching TestRow objects using plain SQL queries */
  implicit def GetResultTestRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[TestRow] = GR {
    prs =>
      import prs._
      TestRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table test. Objects of this class serve as prototypes for rows in queries. */
  class Test(_tableTag: Tag) extends Table[TestRow](_tableTag, "test") {
    def * = (id, name) <> (TestRow.tupled, TestRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({ r => import r._; _1.map(_ => TestRow.tupled((_1.get, _2))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(50,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(50, varying = true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Test */
  lazy val Test = new TableQuery(tag => new Test(tag))

  /**
   * Entity class storing rows of table Proxy
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param appName Database column app_name DBType(VARCHAR), Length(50,true), Default(None)
   *  @param ip Database column ip DBType(VARCHAR), Length(50,true), Default(None)
   *  @param isEnable Database column is_enable DBType(VARCHAR), Length(2,true), Default(None)
   */
  case class ProxyRow(id: Int, appName: Option[String] = None, ip: Option[String] = None, isEnable: Option[String] = None)
  /** GetResult implicit for fetching ProxyRow objects using plain SQL queries */
  implicit def GetResultProxyRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ProxyRow] = GR {
    prs =>
      import prs._
      ProxyRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table proxy. Objects of this class serve as prototypes for rows in queries. */
  class Proxy(_tableTag: Tag) extends Table[ProxyRow](_tableTag, "proxy") {
    def * = (id, appName, ip, isEnable) <> (ProxyRow.tupled, ProxyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, appName, ip, isEnable).shaped.<>({ r => import r._; _1.map(_ => ProxyRow.tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column app_name DBType(VARCHAR), Length(50,true), Default(None) */
    val appName: Column[Option[String]] = column[Option[String]]("app_name", O.Length(50, varying = true), O.Default(None))
    /** Database column ip DBType(VARCHAR), Length(50,true), Default(None) */
    val ip: Column[Option[String]] = column[Option[String]]("ip", O.Length(50, varying = true), O.Default(None))
    /** Database column is_enable DBType(VARCHAR), Length(2,true), Default(None) */
    val isEnable: Column[Option[String]] = column[Option[String]]("is_enable", O.Length(2, varying = true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Proxy */
  lazy val Proxy = new TableQuery(tag => new Proxy(tag))
  
}