package pygman.invoice.invoice.bill.business.ebo;

import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import pygman.invoice.invoice.bill.business.ebi.BillEbi;
import pygman.invoice.invoice.bill.dao.dao.BillDao;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.util.jxl.ExcelUtil;


public class BillEbo implements BillEbi {
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}
	public List<Object[]> getBills(BillQueryModel bqm) {
		return billDao.getBills(bqm);
	}
	public List<OrderDetailModel> getAllBillDetail(BillQueryModel bqm) {
		return billDao.getAllBillDetail(bqm);
	}
	public void writeBillPie(BillQueryModel bqm,OutputStream os) {
		//得到数据
		List<Object[]> temp = billDao.getBills(bqm);
		
		//jfreechart的东西转入到os中
		DefaultPieDataset paramPieDataset = new DefaultPieDataset();
		for(Object[] objs:temp){
			GoodsModel gm = (GoodsModel)objs[0];
			Long num = (Long)objs[1];
			paramPieDataset.setValue(gm.getName(), new Double(num));
		}
		
		JFreeChart localJFreeChart = ChartFactory.createPieChart("采购报表", paramPieDataset, true, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setLabelFont(new Font("SansSerif", 0, 12));
		localPiePlot.setNoDataMessage("对不起，没有对应的数据存在！");
		localPiePlot.setCircular(true);
		localPiePlot.setLabelGap(0.02D);
		
		//localJFreeChart->os
		BufferedImage bi = localJFreeChart.createBufferedImage(500, 370);
		//bi对象如何写入流
		try {
			ImageIO.write(bi, "PNG", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));
		ChartFactory.setChartTheme(theme);
	}

	public InputStream writeBillToExcel(BillQueryModel bqm) throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		//创建Excel文件，将文件的内容写入到流中
		WritableWorkbook b = Workbook.createWorkbook(bos);
		
		//创建工作表
		WritableSheet s = ExcelUtil.cSheet(b, 0, "总括");
		
		//设置行高列宽
		ExcelUtil.sColumnSize(s, 1, 8);
		ExcelUtil.sColumnSize(s, 2, 8);
		ExcelUtil.sColumnSize(s, 3, 25);
		ExcelUtil.sColumnSize(s, 4, 25);
		ExcelUtil.sColumnSize(s, 5, 25);
		
		ExcelUtil.sRowSize(s, 1, 15);
		ExcelUtil.sRowSize(s, 2, 37);
		ExcelUtil.sRowSize(s, 3, 6);
		ExcelUtil.sRowSize(s, 4, 23);
		
		//合并单元格
		ExcelUtil.sMerge(s, 2,2,2,4);
		ExcelUtil.sMerge(s, 3,2,3,5);
		
		//创建单元格
		Label lab22 = ExcelUtil.cLabel(2, 2, "进货统计报表");
		//为单元格设置样式
		ExcelUtil.sLabelStyle(lab22, "黑体", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		//将单元格添加到sheet中
		ExcelUtil.aLabelToSheet(lab22, s);
		
		Label lab25 = ExcelUtil.cLabel(2, 5, "不限");
		ExcelUtil.sLabelStyle(lab25, "黑体", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		ExcelUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = ExcelUtil.cLabel(3, 2, "");
		ExcelUtil.sLabelStyle(lab32, "黑体", 1, Colour.BLACK, Colour.GRAY_50, 1, "2022");
		ExcelUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = ExcelUtil.cLabel(4, 2, "编号");
		ExcelUtil.sLabelStyle(lab42, "黑体", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = ExcelUtil.cLabel(4, 3, "厂商");
		ExcelUtil.sLabelStyle(lab43, "黑体", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab43, s);
		
		Label lab44 = ExcelUtil.cLabel(4, 4, "商品名");
		ExcelUtil.sLabelStyle(lab44, "黑体", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = ExcelUtil.cLabel(4, 5, "数量");
		ExcelUtil.sLabelStyle(lab45, "黑体", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab45, s);
		
		int row = 4;
		int i = 1;
		Long sum = 0L;
		//根据bqm获取数据
		List<Object[]> temp = billDao.getBills(bqm);
		for(Object[] objs:temp){
			GoodsModel gm = (GoodsModel)objs[0];
			Long num = (Long)objs[1];
			Label lab_1 = ExcelUtil.cLabel(row+i, 2, ""+i);
			ExcelUtil.sLabelStyle(lab_1, "宋体", 14, Colour.BLACK, null, 1, "0120");
			ExcelUtil.aLabelToSheet(lab_1, s);
			
			Label lab_2 = ExcelUtil.cLabel(row+i, 3, gm.getGtm().getSm().getName());
			ExcelUtil.sLabelStyle(lab_2, "宋体", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_2, s);
			
			Label lab_3 = ExcelUtil.cLabel(row+i, 4, gm.getName());
			ExcelUtil.sLabelStyle(lab_3, "宋体", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_3, s);
			
			Label lab_4 = ExcelUtil.cLabel(row+i, 5, num.toString());
			ExcelUtil.sLabelStyle(lab_4, "宋体", 14, Colour.BLACK, null, 1, "0112");
			ExcelUtil.aLabelToSheet(lab_4, s);
			
			sum+=num;
			i++;
		}
		
		//合并
		ExcelUtil.sMerge(s,row+i ,2,row+i,4);
		
		Label lab_total = ExcelUtil.cLabel(row+i, 2, "总计:");
		ExcelUtil.sLabelStyle(lab_total, "黑体", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab_total, s);
		
		Label lab_total_num = ExcelUtil.cLabel(row+i, 5, sum.toString());
		ExcelUtil.sLabelStyle(lab_total_num, "黑体", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab_total_num, s);
		
		b.write();
		b.close();
		
		//将文件的内容写入到流中downloadStream
		//如何将一个输出流对象转换成输入流对象：思想错误
		//将输出流对象中的数据加载到输入流对象中
		//将bos中的数据加载到downloadStream中
		return new ByteArrayInputStream(bos.toByteArray());
	}
	
}
