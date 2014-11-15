package pygman.invoice.invoice.bill.web;

import pygman.invoice.invoice.bill.business.ebi.BillEbi;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.invoice.supplier.business.ebi.SupplierEbi;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseAction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class BillAction extends BaseAction {
	public BillQueryModel bqm = new BillQueryModel();
	
	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String list(){
		//加载所有供应商数据
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		//根据查询条件获取对应的报表数据  bqm
		List<Object[]> billList = billEbi.getBills(bqm);
		put("billList",billList);
		return LIST;
	}
	
	//获取报表饼状图
	public String getPieBill() throws IOException{
		//获取到图片，然后将其数据转换成二进制，写入到流中
		HttpServletResponse response  = getResponse();
		OutputStream os = response.getOutputStream();
		//得到jfreechart，将它转换成流，写入os
		//将os对象传入业务层，在业务层内将数据写入os(推荐)
		billEbi.writeBillPie(bqm,os);
		os.flush();
		return null;
	}
	
	//下载Excel格式报表
	private InputStream downloadStream;
	public InputStream getDownloadStream() {
		return downloadStream;
	}
	private String xlsName;
	//做文件名返回
	public String getXlsName() throws UnsupportedEncodingException{
		return new String((xlsName+".xls").getBytes("UTF-8"),"ISO8859-1");
	}
	
	public String getExcelBill() throws Exception{
		//将downloadStream数据初始化好
		//将excel文件的内容写入downloadStream
		xlsName = "报表1";
		downloadStream = billEbi.writeBillToExcel(bqm);
		return "getExcelBill";
	}
	
	//--ajax------------------------------
	private List<OrderDetailModel> odmList;
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}

	//根据商品获取报表明细
	public String ajaxGetGoodsDetail(){
		odmList = billEbi.getAllBillDetail(bqm);
		return "ajaxGetGoodsDetail";
	}
}
