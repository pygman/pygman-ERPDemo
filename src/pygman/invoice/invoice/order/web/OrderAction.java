package pygman.invoice.invoice.order.web;

import java.util.Arrays;
import java.util.List;

import org.hibernate.transform.ToListResultTransformer;
import pygman.invoice.auth.emp.business.ebi.EmpEbi;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.goods.business.ebi.GoodsEbi;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.invoice.order.business.ebi.OrderEbi;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import pygman.invoice.invoice.store.business.ebi.StoreEbi;
import pygman.invoice.invoice.store.vo.StoreModel;
import pygman.invoice.invoice.supplier.business.ebi.SupplierEbi;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseAction;

public class OrderAction extends BaseAction {
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	public String list(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}

	public String input(){
		//进入页面时，加载具有商品的供应商信息
		//由供应商关联类别，由类别关联商品
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		//进入页面时，加载第一个供应商的商品类别信息(不包含没有商品的类别信息)
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnionBySupplierUuid(supplierList.get(0).getUuid());
		//进入页面时，加载第一个供应商的第一个商品类别对应的所有商品信息
		List<GoodsModel> gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		
		put("supplierList",supplierList);
		put("gtmList",gtmList);
		put("gmList",gmList);
		
		if(om.getUuid() != null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}
	
	//保存采购订单
	//提交的订单中的货物uuid
	public Long[] goodsUuids;
	//提交的订单中的采购数量
	public Integer[] nums;
	//提交的订单中的采购单价
	public Double[] prices;
	public String buySave(){
//		System.out.println(nums.length);
//		System.out.println(prices.length);
//		System.out.println(goodsUuids.length);
		//此时收集的数据包含有，三个数组+一个供应商uuid（包含在om对象中）
		orderEbi.save(om,goodsUuids,nums,prices,getLogin());
		return TO_LIST;
	}
	
	//采购明细
	public String buyDetail(){
		//获取数据，展示
		om = orderEbi.get(om.getUuid());
		return "buyDetail";
	}
	
	//采购审核列表
	public String checkList(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "checkList";
	}
	
	//进入采购订单审核详情页
	public String buyCheckDetail(){
		//根据传递过来的要审核的订单uuid查询对应的订单信息，在详情页展示
		om = orderEbi.get(om.getUuid());
		return "buyCheckDetail";
	}
	
	//采购审核通过
	public String buyCheckPass(){
		orderEbi.buyCheckPass(om.getUuid(),getLogin());
		return "toCheckList";
	}
	
	//采购审核通过
	public String buyCheckNoPass(){
		orderEbi.buyCheckNoPass(om.getUuid(),getLogin());
		return "toCheckList";
	}
	
	//运输任务相关-------------------------------------------------
	public String transportList(){
		setDataTotal(orderEbi.getTransportCount(oqm));
		List<OrderModel> orderList = orderEbi.getAllTransport(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}
	
	//运输任务指派
	public String assignTask(){
		//加载当前登陆人所属部门的所有员工信息,必须使用部门编号重新查询
		Long depUuid = getLogin().getDm().getUuid();
		List<EmpModel> empList = empEbi.getAllByDepUuid(depUuid);
		put("empList",empList);
		om = orderEbi.get(om.getUuid());
		return "assignTask";
	}
	
	//指派任务
	public String task(){
		//om对象中封装了任务订单的uuid(om.uuid)和对应的跟单人uuid(om.completer.uuid)
		orderEbi.task(om.getUuid(),om.getCompleter().getUuid());
		return "toTransportList";
	}
	
	//运输任务(登陆人)查询
	public String tasks(){
		setDataTotal(orderEbi.getTasksCount(oqm,getLogin()));
		List<OrderModel> orderList = orderEbi.getAllTasks(oqm,pageNum,pageCount,getLogin());
		put("orderList", orderList);
		return "tasks";
	}
	
	//运输任务详情
	public String taskDetail(){
		om = orderEbi.get(om.getUuid());
		return "taskDetail";
	}
	
	//结单
	public String endTask(){
		orderEbi.endTask(om.getUuid());
		return "toTasks";
	}
	
	//入库相关-------------------------------
	//入库列表
	public String inList(){
		//展示订单信息：等待入库的订单信息，其他的不显示
		setDataTotal(orderEbi.getInStoreCount(oqm));
		List<OrderModel> orderList = orderEbi.getAllInStore(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "inList";
	}
	
	//入库详情
	public String inDetail(){
		//加载所有的仓库信息
		List<StoreModel> storeList = storeEbi.getAll();
		put("storeList",storeList);
		om = orderEbi.get(om.getUuid());
		return "inDetail";
	}
	
	
	//--AJAX--------------------------------
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String used;
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;
	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}

	public String ajaxGetGtmAndGm(){
		//根据供应商uuid获取商品类别（不包含没有商品的类别）
		gtmList = goodsTypeEbi.getAllUnionBySupplierUuid(supplierUuid);
		//根据第一个类别uuid获取商品信息
		gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		//第一个商品
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}

	//动态添加行使用的方法：要进行数据过滤，过滤使用过的数据
	public String ajaxGetGtmAndGm2(){
		//将used转换成一个新的模型，即可以用于进行包含性检测，又不至于出现like形式的包含
		//Set<Long> usedUuids = new HashSet<Long>();
		//string->set
		String[] usedArr = used.split(",");
		//string[]->set
		List<String> usedUuids = Arrays.asList(usedArr);
		//list->set(自己扩展)
		
		//根据供应商uuid获取商品类别（不包含没有商品的类别）
		//如果某个类别中的商品都已经使用了，那么该类别也应该被删选掉
		gtmList = goodsTypeEbi.getAllUnionBySupplierUuid(supplierUuid);
		GOODSTYPE:
		for(int i = gtmList.size()-1 ;i>=0;i--){
			//按某种条件删除没有可用商品的类别信息
			//如果该类别中所有的商品都使用过，则该类别删除
			//另一种说法：如果某种类别中的某个商品在使用列表中未出现过，保留（推荐）
			GoodsTypeModel temp = gtmList.get(i);
			for(GoodsModel temp2: temp.getGoodses()){
				//如果该商品对应的uuid在使用名单中未出现过，该类别保留，直接检测下一个类别
				if(!usedUuids.contains(temp2.getUuid().toString())){
					continue GOODSTYPE;
				}
			}
			//到达此行说明上述循环执行完毕了，执行完毕意味着对应的商品类别对象temp中所有的商品都在已使用名单中出现过
			gtmList.remove(i);
		}
		//根据第一个类别uuid获取商品信息
		gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		//当前获取的商品如果出现过，将该商品删除
		//过滤已经使用过的商品
		for(int i = gmList.size()-1;i>=0;i--){
			if(usedUuids.contains(gmList.get(i).getUuid().toString())){
				gmList.remove(i);
			}
		}
		//第一个商品
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	public String ajaxGetGm(){
		String[] usedArr = used.split(",");
		List<String> usedUuids = Arrays.asList(usedArr);
		//根据第一个类别uuid获取商品信息
		gmList = goodsEbi.getAllByGtmUuid(goodsTypeUuid);
		for(int i = gmList.size()-1;i>=0;i--){
			if(usedUuids.contains(gmList.get(i).getUuid().toString())){
				gmList.remove(i);
			}
		}
		//第一个商品
		gm = gmList.get(0);
		return "ajaxGetGm";
	}
	
	public String ajaxGetOne(){
		//第一个商品
		gm = goodsEbi.get(goodsUuid);
		return "ajaxGetOne";
	}
}












