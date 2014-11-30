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
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Test.ddl
  
  /** Entity class storing rows of table Test
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(50,true), Default(None) */
  case class TestRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching TestRow objects using plain SQL queries */
  implicit def GetResultTestRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[TestRow] = GR{
    prs => import prs._
    TestRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table test. Objects of this class serve as prototypes for rows in queries. */
  class Test(_tableTag: Tag) extends Table[TestRow](_tableTag, "test") {
    def * = (id, name) <> (TestRow.tupled, TestRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({r=>import r._; _1.map(_=> TestRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(50,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(50,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Test */
  lazy val Test = new TableQuery(tag => new Test(tag))
}