package pygman.invoice.invoice.goodstype.web;

import pygman.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeQueryModel;
import pygman.invoice.invoice.supplier.business.ebi.SupplierEbi;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseAction;

import java.util.List;

public class GoodsTypeAction extends BaseAction {
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;
	
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	public String input(){
		//加载所有供应商数据
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		if(gm.getUuid() != null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(gm.getUuid() == null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
	
	//--AJAX---------------------------------------
	//1.页面设置ajax(jquery格式)请求
	//$.post("....action",{"name":value},function(){})
	//2.完成请求对应的功能
	//3.设置请求返回类型为json(struts.xml)设置type=json,extends="json-default"
	//4.设置数据返回格式<param name="root">		<param name="includeProperties">	<param name="excludeProperties">,要求数据提供get方法
	//5.页面接收数据
	//6.使用jquery对象操作完成对象组合与操作（select中添加option）
	
	public Long supplierUuid;
	private List<GoodsTypeModel> gtmList;
	//为了使用我们得到的数据返回必须提供一个get方法
	public List<GoodsTypeModel> getGtmList(){
		return gtmList;
	}
	
	public String ajaxGetAllBySupplierUuid(){
		//根据传递过来的供应商uuid参数值获取对应的商品类别信息
		gtmList = goodsTypeEbi.getAllBySupplierUuid(supplierUuid);
		//ajax请求向前端返回数据：String,json,xml
		//此处选用json格式
		//由一个Java集合对象/Java对象转换成json格式如何转换？
		//struts预料到在action中将对象转换成json的任务量巨大，因此开发了一个jar包插件
		//struts2-json-plugin-2.3.7.jar
		//转换依据
		/*
		List,Set->数组
		Object,Model->对象{"属性名":属性值}
		name aa age 33 - > {"name":"aa","age":33}
		List<Model>->[{},{},{}]
		return "aaa";
		*/
		//转换的过程通过result完成的
		return "ajaxGetAllBySupplierUuid";
	}
	
	/*public String getAbc(){
		return "aaa";
	}
	public Double getDef(){
		return 12345.678d;
	}
	//{"abc":"aaa","def":12345.678}
	*/
	/*
	{"gtmList":
			[
			 	{
			 		"name":"黄金叶",
			 		"sm":{"address":"河南省郑州市烟草街","contact":"刘烟草","name":"河南省烟草总公司","needs":0,"needsView":"自提","tele":"99999999","uuid":3},
			 		"uuid":7
			 	},
			 	{
			 		"name":"红旗渠",
			 		"sm":{"address":"河南省郑州市烟草街","contact":"刘烟草","name":"河南省烟草总公司","needs":0,"needsView":"自提","tele":"99999999","uuid":3},
			 		"uuid":8
			 	},
			 	{
			 		"name":"金芒果",
			 		"sm":{"address":"河南省郑州市烟草街","contact":"刘烟草","name":"河南省烟草总公司","needs":0,"needsView":"自提","tele":"99999999","uuid":3},
			 		"uuid":9
			 	}
			]
	}
	*/
	
}
