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
  lazy val ddl = Test.ddl ++ Proxy.ddl ++ ExportContractProgress.ddl

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

  /**
   * Entity class storing rows of table ExportContractProgress
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param month Database column month DBType(VARCHAR), Length(10,true), Default(None)
   *  @param exportContractDate Database column export_contract_date DBType(VARCHAR), Length(10,true), Default(None)
   *  @param exportContractNum Database column export_contract_num DBType(VARCHAR), Length(50,true), Default(None)
   *  @param contractStatus Database column contract_status DBType(VARCHAR), Length(20,true), Default(None)
   *  @param customer Database column customer DBType(VARCHAR), Length(50,true), Default(None)
   *  @param country Database column country DBType(VARCHAR), Length(50,true), Default(None)
   *  @param product Database column product DBType(VARCHAR), Length(50,true), Default(None)
   *  @param exportContractMoney Database column export_contract_money DBType(VARCHAR), Length(50,true), Default(None)
   *  @param settlementWay Database column settlement_way DBType(VARCHAR), Length(10,true), Default(None)
   *  @param latestDeliveryDate Database column latest_delivery_date DBType(VARCHAR), Length(50,true), Default(None)
   *  @param transportWay Database column transport_way DBType(VARCHAR), Length(50,true), Default(None)
   *  @param purchaseContractNum Database column purchase_contract_num DBType(VARCHAR), Length(50,true), Default(None)
   *  @param purchaseMoney Database column purchase_money DBType(VARCHAR), Length(50,true), Default(None)
   *  @param progressDescription Database column progress_description DBType(VARCHAR), Length(100,true), Default(None)
   *  @param planLeaveFactoryDate Database column plan_leave_factory_date DBType(VARCHAR), Length(50,true), Default(None)
   *  @param planSendDate Database column plan_send_date DBType(VARCHAR), Length(50,true), Default(None)
   *  @param planArrivalData Database column plan_arrival_data DBType(VARCHAR), Length(50,true), Default(None)
   *  @param payDescription Database column pay_description DBType(VARCHAR), Length(50,true), Default(None)
   *  @param comment Database column comment DBType(VARCHAR), Length(500,true), Default(None)
   */
  case class ExportContractProgressRow(id: Int, month: Option[String] = None, exportContractDate: Option[String] = None, exportContractNum: Option[String] = None, contractStatus: Option[String] = None, customer: Option[String] = None, country: Option[String] = None, product: Option[String] = None, exportContractMoney: Option[String] = None, settlementWay: Option[String] = None, latestDeliveryDate: Option[String] = None, transportWay: Option[String] = None, purchaseContractNum: Option[String] = None, purchaseMoney: Option[String] = None, progressDescription: Option[String] = None, planLeaveFactoryDate: Option[String] = None, planSendDate: Option[String] = None, planArrivalData: Option[String] = None, payDescription: Option[String] = None, comment: Option[String] = None)
  /** GetResult implicit for fetching ExportContractProgressRow objects using plain SQL queries */
  implicit def GetResultExportContractProgressRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ExportContractProgressRow] = GR {
    prs =>
      import prs._
      ExportContractProgressRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table export_contract_progress. Objects of this class serve as prototypes for rows in queries. */
  class ExportContractProgress(_tableTag: Tag) extends Table[ExportContractProgressRow](_tableTag, "export_contract_progress") {
    def * = (id, month, exportContractDate, exportContractNum, contractStatus, customer, country, product, exportContractMoney, settlementWay, latestDeliveryDate, transportWay, purchaseContractNum, purchaseMoney, progressDescription, planLeaveFactoryDate, planSendDate, planArrivalData, payDescription, comment) <> (ExportContractProgressRow.tupled, ExportContractProgressRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, month, exportContractDate, exportContractNum, contractStatus, customer, country, product, exportContractMoney, settlementWay, latestDeliveryDate, transportWay, purchaseContractNum, purchaseMoney, progressDescription, planLeaveFactoryDate, planSendDate, planArrivalData, payDescription, comment).shaped.<>({ r => import r._; _1.map(_ => ExportContractProgressRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column month DBType(VARCHAR), Length(10,true), Default(None) */
    val month: Column[Option[String]] = column[Option[String]]("month", O.Length(10, varying = true), O.Default(None))
    /** Database column export_contract_date DBType(VARCHAR), Length(10,true), Default(None) */
    val exportContractDate: Column[Option[String]] = column[Option[String]]("export_contract_date", O.Length(10, varying = true), O.Default(None))
    /** Database column export_contract_num DBType(VARCHAR), Length(50,true), Default(None) */
    val exportContractNum: Column[Option[String]] = column[Option[String]]("export_contract_num", O.Length(50, varying = true), O.Default(None))
    /** Database column contract_status DBType(VARCHAR), Length(20,true), Default(None) */
    val contractStatus: Column[Option[String]] = column[Option[String]]("contract_status", O.Length(20, varying = true), O.Default(None))
    /** Database column customer DBType(VARCHAR), Length(50,true), Default(None) */
    val customer: Column[Option[String]] = column[Option[String]]("customer", O.Length(50, varying = true), O.Default(None))
    /** Database column country DBType(VARCHAR), Length(50,true), Default(None) */
    val country: Column[Option[String]] = column[Option[String]]("country", O.Length(50, varying = true), O.Default(None))
    /** Database column product DBType(VARCHAR), Length(50,true), Default(None) */
    val product: Column[Option[String]] = column[Option[String]]("product", O.Length(50, varying = true), O.Default(None))
    /** Database column export_contract_money DBType(VARCHAR), Length(50,true), Default(None) */
    val exportContractMoney: Column[Option[String]] = column[Option[String]]("export_contract_money", O.Length(50, varying = true), O.Default(None))
    /** Database column settlement_way DBType(VARCHAR), Length(10,true), Default(None) */
    val settlementWay: Column[Option[String]] = column[Option[String]]("settlement_way", O.Length(10, varying = true), O.Default(None))
    /** Database column latest_delivery_date DBType(VARCHAR), Length(50,true), Default(None) */
    val latestDeliveryDate: Column[Option[String]] = column[Option[String]]("latest_delivery_date", O.Length(50, varying = true), O.Default(None))
    /** Database column transport_way DBType(VARCHAR), Length(50,true), Default(None) */
    val transportWay: Column[Option[String]] = column[Option[String]]("transport_way", O.Length(50, varying = true), O.Default(None))
    /** Database column purchase_contract_num DBType(VARCHAR), Length(50,true), Default(None) */
    val purchaseContractNum: Column[Option[String]] = column[Option[String]]("purchase_contract_num", O.Length(50, varying = true), O.Default(None))
    /** Database column purchase_money DBType(VARCHAR), Length(50,true), Default(None) */
    val purchaseMoney: Column[Option[String]] = column[Option[String]]("purchase_money", O.Length(50, varying = true), O.Default(None))
    /** Database column progress_description DBType(VARCHAR), Length(100,true), Default(None) */
    val progressDescription: Column[Option[String]] = column[Option[String]]("progress_description", O.Length(100, varying = true), O.Default(None))
    /** Database column plan_leave_factory_date DBType(VARCHAR), Length(50,true), Default(None) */
    val planLeaveFactoryDate: Column[Option[String]] = column[Option[String]]("plan_leave_factory_date", O.Length(50, varying = true), O.Default(None))
    /** Database column plan_send_date DBType(VARCHAR), Length(50,true), Default(None) */
    val planSendDate: Column[Option[String]] = column[Option[String]]("plan_send_date", O.Length(50, varying = true), O.Default(None))
    /** Database column plan_arrival_data DBType(VARCHAR), Length(50,true), Default(None) */
    val planArrivalData: Column[Option[String]] = column[Option[String]]("plan_arrival_data", O.Length(50, varying = true), O.Default(None))
    /** Database column pay_description DBType(VARCHAR), Length(50,true), Default(None) */
    val payDescription: Column[Option[String]] = column[Option[String]]("pay_description", O.Length(50, varying = true), O.Default(None))
    /** Database column comment DBType(VARCHAR), Length(500,true), Default(None) */
    val comment: Column[Option[String]] = column[Option[String]]("comment", O.Length(500, varying = true), O.Default(None))
  }
  /** Collection-like TableQuery object for table ExportContractProgress */
  lazy val ExportContractProgress = new TableQuery(tag => new ExportContractProgress(tag))

}