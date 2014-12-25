package com.jeff.services

import java.io.FileOutputStream
import scala.slick.driver.MySQLDriver.simple.columnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.intColumnType
import scala.slick.driver.MySQLDriver.simple.optionColumnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.productQueryToUpdateInvoker
import scala.slick.driver.MySQLDriver.simple.queryToAppliedQueryInvoker
import scala.slick.driver.MySQLDriver.simple.queryToDeleteInvoker
import scala.slick.driver.MySQLDriver.simple.queryToInsertInvoker
import scala.slick.driver.MySQLDriver.simple.repToQueryExecutor
import scala.slick.driver.MySQLDriver.simple.stringColumnType
import scala.slick.driver.MySQLDriver.simple.stringOptionColumnExtensionMethods
import scala.slick.driver.MySQLDriver.simple.valueToConstColumn
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.util.Region
import org.slf4j.LoggerFactory
import com.jeff.Constant
import com.jeff.entities.Query.Expression
import com.jeff.entities.Query.Filter
import com.jeff.entities.Query.Order
import com.jeff.entities.Query.Sort
import com.jeff.entities.Tables
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.hssf.usermodel.HSSFFont
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.ss.usermodel.IndexedColors
import java.io.ByteArrayOutputStream

/**
 * 合同进程
 * Created by fangj on 2014/11/21.
 */
trait ContractStatusService extends BaseService {

  def logger = LoggerFactory.getLogger(classOf[ContractStatusService])

  def getById(id: Int) = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.filter(_.id === id).first
    }
  }

  def count(filter: Option[Array[Filter]]): Int = {
    db.withSession { implicit session =>
      getFilter(filter).query.length.run
    }
  }

  /**
   * 获取过滤后的Proxy
   */
  private def getFilter(filter: Option[Array[Filter]]) = {

    val (id, idExpression): (Option[Int], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("id")) match {
          case Some(filter) => (Some(filter.value.toString.toInt), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (month, monthExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("month")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (exportContractNum, exportContractNumExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("exportContractNum")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (contractStatus, contractStatusExpression): (Option[String], Option[Expression.Value]) = filter match {
      case Some(filter) => {
        filter.find(_.property.equals("contractStatus")) match {
          case Some(filter) => (Some(filter.value.toString), filter.expression)
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    Tables.ExportContractProgress.filteredBy(id) { _.id === id }
      .filteredBy(month)(_.month === month)
      .filteredBy(exportContractNum) {
        exportContractNumExpression match {
          case Some(Expression.EQ) => _.exportContractNum === exportContractNum
          case Some(Expression.LIKE) => _.exportContractNum like exportContractNum.map("%" + _ + "%").getOrElse("")
          case _ => _.exportContractNum === exportContractNum
        }
      }.filteredBy(contractStatus)(_.contractStatus === contractStatus)

  }

  /**
   * 排序
   */
  private def sort[X, Y](query: scala.slick.lifted.Query[com.jeff.entities.Tables.ExportContractProgress, Y, Seq], sort: Option[Array[Sort]]) = {

    val (id, idOrder) = sort match {
      case Some(sort) => {
        sort.find(_.property.equals("id")) match {
          case Some(sort) => (Some(sort.property), Some(sort.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (month, monthOrder) = sort match {
      case Some(month) => {
        month.find(_.property.equals("month")) match {
          case Some(month) => (Some(month.property), Some(month.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (exportContractNum, exportContractNumOrder) = sort match {
      case Some(exportContractNum) => {
        exportContractNum.find(_.property.equals("exportContractNum")) match {
          case Some(exportContractNum) => (Some(exportContractNum.property), Some(exportContractNum.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    val (contractStatus, contractStatusOrder) = sort match {
      case Some(contractStatus) => {
        contractStatus.find(_.property.equals("contractStatus")) match {
          case Some(contractStatus) => (Some(contractStatus.property), Some(contractStatus.direction))
          case None => (None, None)
        }
      }
      case None => (None, None)
    }

    query.sortedBy(id) {
      idOrder match {
        case Some(Order.ASC) => _.id.asc
        case Some(Order.DESC) => _.id.desc
        case _ => _.id.desc
      }
    }.sortedBy(month) {
      monthOrder match {
        case Some(Order.ASC) => _.month.asc
        case Some(Order.DESC) => _.month.desc
        case _ => _.month.desc
      }
    }.sortedBy(exportContractNum) {
      exportContractNumOrder match {
        case Some(Order.ASC) => _.exportContractNum.asc
        case Some(Order.DESC) => _.exportContractNum.desc
        case _ => _.exportContractNum.desc
      }
    }.sortedBy(contractStatus) {
      contractStatusOrder match {
        case Some(Order.ASC) => _.contractStatus.asc
        case Some(Order.DESC) => _.contractStatus.desc
        case _ => _.contractStatus.desc
      }
    }

  }

  def search(offset: Option[Int], limit: Option[Int], sort: Option[Array[Sort]], filter: Option[Array[Filter]]) = {
    implicit class toQuery(query: scala.slick.lifted.Query[com.jeff.entities.Tables.ExportContractProgress, com.jeff.entities.Tables.ExportContractProgress#TableElementType, Seq]) {
      def order(sort: Option[Array[Sort]]) = new ContractStatusService {}.sort(query, sort)
    }
    db.withSession { implicit session =>
      getFilter(filter).query.drop(offset.getOrElse(0)).take(limit.getOrElse(Constant.PAGESIZE)).order(sort).query.list
    }
  }

  def search(offset: Option[Int], limit: Option[Int], filter: Option[Array[Filter]]) = {
    db.withSession { implicit session =>
      getFilter(filter).query.drop(offset.getOrElse(0)).take(limit.getOrElse(Constant.PAGESIZE)).list
    }
  }

  def update(contract: Tables.ExportContractProgressRow) = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.filter(_.id === contract.id).update(contract)
    }
  }

  def delete(id: Int) = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.filter(_.id === id).delete
    }
  }

  def save(contract: Tables.ExportContractProgressRow): Int = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.insert(contract)
    }
  }

  def saveOrUpdate(contract: Tables.ExportContractProgressRow): Int = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.insertOrUpdate(contract)
    }
  }

  def all(): List[Tables.ExportContractProgressRow] = {
    db.withSession { implicit session =>
      Tables.ExportContractProgress.list
    }
  }

  /**
   * 导出所有的记录
   */
  def export(): Array[Byte] = {

    val heads = Array("序号", "月份", "出口合同签订日", "出口合同号", "合同状态", "客户方", "国别", "合同产品", "出口合同金额（USD)", "结算方式", "", "运输方式及箱型", "采购合同号", "采购合同金额(RMB)", "合同进展", "预计出厂日", "预计发货日", "预计到港日", "是否已到款", "备注")

    val baos = new ByteArrayOutputStream()

    val wb = new HSSFWorkbook()
    val sheet = wb.createSheet()

    val format = wb.createDataFormat()
    // 合并单元格(构造模板格式)
    sheet.addMergedRegion(CellRangeAddress.valueOf("A1:T1"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("B2:B3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("C2:C3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("D2:D3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("E2:E3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("F2:F3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("G2:G3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("H2:H3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("I2:I3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("J2:K2"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("L2:L3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("M2:M3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("N2:N3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("O2:O3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("P2:P3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("Q2:Q3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("R2:R3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("S2:S3"))
    sheet.addMergedRegion(CellRangeAddress.valueOf("T2:T3"))

    // 列宽度自适应
    heads.zipWithIndex foreach {
      case (e, index) => {
        sheet.setColumnWidth(index, 20 * 256)
        sheet.autoSizeColumn(index)
      }
    }

    // 标题字体
    val titleFont = wb.createFont()
    titleFont.setBold(true)
    titleFont.setFontHeight(300) // 设置字体大小   
    titleFont.setFontName("宋体") // 设置单元格字体     

    // 标题合并单元格样式
    val titleMergedStyle = wb.createCellStyle()
    titleMergedStyle.setAlignment(CellStyle.ALIGN_CENTER)
    titleMergedStyle.setFont(titleFont)
    titleMergedStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER)
    titleMergedStyle.setBorderBottom(CellStyle.BORDER_THIN)
    titleMergedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex())

    // 列样式
    val columnStyle = wb.createCellStyle()
    columnStyle.setBorderBottom(CellStyle.BORDER_THIN)
    columnStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex())
    columnStyle.setBorderLeft(CellStyle.BORDER_THIN)
    columnStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex())
    columnStyle.setBorderRight(CellStyle.BORDER_THIN)
    columnStyle.setRightBorderColor(IndexedColors.BLACK.getIndex())
    columnStyle.setBorderTop(CellStyle.BORDER_THIN)
    columnStyle.setTopBorderColor(IndexedColors.BLACK.getIndex())
    columnStyle.setAlignment(CellStyle.ALIGN_CENTER)
    columnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER)

    // 金额样式
    val moneyColumnStyle = wb.createCellStyle()
    moneyColumnStyle.setBorderBottom(CellStyle.BORDER_THIN)
    moneyColumnStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex())
    moneyColumnStyle.setBorderLeft(CellStyle.BORDER_THIN)
    moneyColumnStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex())
    moneyColumnStyle.setBorderRight(CellStyle.BORDER_THIN)
    moneyColumnStyle.setRightBorderColor(IndexedColors.BLACK.getIndex())
    moneyColumnStyle.setBorderTop(CellStyle.BORDER_THIN)
    moneyColumnStyle.setTopBorderColor(IndexedColors.BLACK.getIndex())
    moneyColumnStyle.setAlignment(CellStyle.ALIGN_CENTER)
    moneyColumnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER)
    moneyColumnStyle.setDataFormat(format.getFormat("#,##0.00"));

    // 日期样式
    val dateColumnStyle = wb.createCellStyle()
    dateColumnStyle.setBorderBottom(CellStyle.BORDER_THIN)
    dateColumnStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex())
    dateColumnStyle.setBorderLeft(CellStyle.BORDER_THIN)
    dateColumnStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex())
    dateColumnStyle.setBorderRight(CellStyle.BORDER_THIN)
    dateColumnStyle.setRightBorderColor(IndexedColors.BLACK.getIndex())
    dateColumnStyle.setBorderTop(CellStyle.BORDER_THIN)
    dateColumnStyle.setTopBorderColor(IndexedColors.BLACK.getIndex())
    dateColumnStyle.setAlignment(CellStyle.ALIGN_CENTER)
    dateColumnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER)
    dateColumnStyle.setDataFormat(format.getFormat("yyyy-MM-dd"))

    val titleRow = sheet.createRow(0)
    titleRow.setHeightInPoints((3 * sheet.getDefaultRowHeightInPoints()))
    val cell = titleRow.createCell(0)
    cell.setCellStyle(titleMergedStyle)
    cell.setCellValue("出口合同进程表")

    val headRow = sheet.createRow(1)
    headRow.setHeightInPoints(20)

    val headRow2 = sheet.createRow(2)
    headRow2.setHeightInPoints(20)

    // 合并的特殊列头
    val ttcell = headRow2.createCell(9)
    ttcell.setCellStyle(columnStyle)
    ttcell.setCellValue("T/T")
    val lccell = headRow2.createCell(10)
    lccell.setCellStyle(columnStyle)
    lccell.setCellValue("LC最晚交货期")

    // 设置列头
    heads.zipWithIndex foreach {
      case (e, index) => {
        val cell = headRow.createCell(index)
        cell.setCellStyle(columnStyle)
        cell.setCellValue(e)
        if (index != 9 && index != 10) {
          val blankcell = headRow2.createCell(index)
          blankcell.setCellStyle(columnStyle)
          blankcell.setCellValue("")
        }
      }
    }

    // 查询数据
    val demoDatas = List(Array("3月", "20131113", "MSC-USA-13E01", "待出运", "THE FEDERAL GROUP", "美国", "GK0112 GASKET", "13478", "T/T", "", "海运 LCL", "200131113-1", "70140", "已订12月21日船", "20141213", "20141221", "", "已到100%", "代理出口合同"),
      Array("3月", "20131113", "MSC-USA-13E01", "待出运", "THE FEDERAL GROUP", "美国", "GK0112 GASKET", "13478", "T/T", "", "海运 LCL", "200131113-1", "70140", "已订12月21日船", "20141213", "20141221", "", "已到100%", "代理出口合同"),
      Array("3月", "20131113", "MSC-USA-13E01", "待出运", "THE FEDERAL GROUP", "美国", "GK0112 GASKET", "13478", "T/T", "", "海运 LCL", "200131113-1", "70140", "已订12月21日船", "20141213", "20141221", "", "已到100%", "代理出口合同"),
      Array("4月", "20131113", "MSC-USA-13E01", "待出运", "THE FEDERAL GROUP", "美国", "GK0112 GASKET", "13478", "T/T", "", "海运 LCL", "200131113-1", "70140", "已订12月21日船", "20141213", "20141221", "", "已到100%", "代理出口合同"))

    // 合并单元格的起始号
    var startNum = 4
    // 合并单元格的计数器
    var count = 0
    // 数据填充
    demoDatas.zipWithIndex foreach { datas =>
      {
        val dataRow = sheet.createRow(3 + datas._2)
        count = count + 1
        val month = datas._1(0)
        // 取下一个月份(防止索引越界)
        val nextMonth = if (datas._2 + 1 == demoDatas.size) month else demoDatas(datas._2 + 1)(0)
        dataRow.setHeightInPoints(20)
        val cell = dataRow.createCell(0)
        cell.setCellStyle(columnStyle)
        cell.setCellValue(datas._2 + 1)
        datas._1.zipWithIndex foreach {
          case (e, index) => {
            val cell = dataRow.createCell(index + 1)
            if (index == 7 || index == 12) {
              cell.setCellStyle(moneyColumnStyle)
              // 小数格式化
              cell.setCellValue(e.toDouble)
            } else if (index == 1 || index == 14 || index == 15) {
              cell.setCellStyle(dateColumnStyle)
              // 日期格式化
              val format = new java.text.SimpleDateFormat("yyyyMMdd")
              cell.setCellValue(format.parse(e))
            } else {
              cell.setCellStyle(columnStyle)
              cell.setCellValue(e)
            }
          }
        }
        if (!nextMonth.equals(month) && count > 1) {
          // 合并单元格
          sheet.addMergedRegion(CellRangeAddress.valueOf(s"B${startNum}:B${startNum + count - 1}"))
          // 更改下一个合并的开始号
          startNum = startNum + count
          // 重置计数器
          count = 0
        }
      }
    }

    // 最后的合并
    if (count != 0 && count > 1) {
      sheet.addMergedRegion(CellRangeAddress.valueOf(s"B${startNum}:B${startNum + count - 1}"))
    }

    wb.write(baos)
    baos.toByteArray()

  }

}