package pygman.invoice.invoice.order.business.ebo;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.order.business.ebi.OrderEbi;
import pygman.invoice.invoice.order.dao.dao.OrderDao;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.util.base.BaseQueryModel;
import pygman.invoice.util.exception.AppException;
import pygman.invoice.util.format.MD5Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderEbo implements OrderEbi {
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

	public void save(OrderModel om, Long[] goodsUuids, Integer[] nums,	Double[] prices, EmpModel creater) {
		//保存订单
		//订单号采用当前系统时间+当前登陆人
		om.setOrderNum(MD5Utils.md5("" + System.currentTimeMillis() + creater.getUuid()));
		
		//设置当前系统时间为下单时间
		om.setCreateTime(System.currentTimeMillis());
		//设置订单类别为采购订单
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//设置订单状态
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//设置申请人
		om.setCreater(creater);
		
		//保存订单的同时是否保存明细数据————级联添加cascade=save-update inverse=true
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		int totalNum = 0;
		double totalPrice = 0.0d;
		for(int i = 0;i<goodsUuids.length;i++){
			//创建订单明细对象
			OrderDetailModel odm = new OrderDetailModel();
			//设置订单明细的数量
			odm.setNum(nums[i]);
			//设置订单中的剩余数量
			odm.setSurplus(nums[i]);
			//设置订单明细的单价
			odm.setPrice(prices[i]);
			//设置订单明细所属的订单(重点)
			odm.setOm(om);
			//组织订单明细中的货物数据
			GoodsModel gm = new GoodsModel();
			Long goodsUuid = goodsUuids[i];
			gm.setUuid(goodsUuid);
			//设置订单明细中的货物
			odm.setGm(gm);
			//将该订单明细添加到订单集合中
			odms.add(odm);
			
			totalNum+=nums[i];
			totalPrice += nums[i]*prices[i];
		}
		//设置订单与订单明细间的关系
		om.setOdms(odms);
		//设置订单总数量
		om.setTotalNum(totalNum);
		//设置订单总价格
		om.setTotalPrice(totalPrice);
		
		orderDao.save(om);
	}

	public void buyCheckPass(Long uuid, EmpModel checker) {
		//审核通过什么意思？type变化为审核通过状态，要不要添加审核人？要不要添加审核时间？
		//update更新？快照更新？快照更新为首选
		OrderModel om = orderDao.get(uuid);
		//进行逻辑校验——对操作的安全性进行保障
		//要进行审核前提是订单状态为没有审核
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("请不要进行非法操作！");
		}
		//设置审核人
		om.setChecker(checker);
		//设置审核时间
		om.setCheckTime(System.currentTimeMillis());
		//设置订单状态为已经审核通过
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
	}

	public void buyCheckNoPass(Long uuid, EmpModel checker) {
		//审核通过什么意思？type变化为审核通过状态，要不要添加审核人？要不要添加审核时间？
		//update更新？快照更新？快照更新为首选
		OrderModel om = orderDao.get(uuid);
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("请不要进行非法操作！");
		}
		//设置审核人
		om.setChecker(checker);
		//设置审核时间
		om.setCheckTime(System.currentTimeMillis());
		//设置订单状态为审核驳回
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
	}
	//表示采购相关的运输模块对应的状态
	private Integer[] types = {
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,	OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,  	OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		};
	
	public Integer getTransportCount(OrderQueryModel oqm) {
		return orderDao.getCount(oqm,types);
	}

	public List<OrderModel> getAllTransport(OrderQueryModel oqm,Integer pageNum, Integer pageCount) {
		//需要的数据是什么样的？
		//订单的类型应该是已经审核通过的，已分配任务人，已经完成，入库完毕
		//select *** from *** where type in (1,2,3) 
		return orderDao.getAll(oqm,pageNum,pageCount,types);
	}

	public void task(Long orderUuid, Long completerUuid) {
		//快照更新
		//1.查询数据
		OrderModel om = orderDao.get(orderUuid);
		//逻辑校验
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("请不要进行非法操作！");
		}
		//2.修改数据
		//修改订单状态
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		//修改跟单人
		EmpModel completer = new EmpModel();
		completer.setUuid(completerUuid);
		om.setCompleter(completer);
	}
	
	
	//下面两个方法，将查询条件作为通用查询条件中的一部分进行传递的
	public int getTasksCount(OrderQueryModel oqm, EmpModel login) {
		/*
		oqm.setCompleter(login);
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		return orderDao.getCount(oqm);
		*/
		oqm.setCompleter(login);
		return orderDao.getCount(oqm,taskTypes);
		
	}
	
	private Integer[] taskTypes = {
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,  	
			OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		};
	
	public List<OrderModel> getAllTasks(OrderQueryModel oqm, Integer pageNum,Integer pageCount, EmpModel login) {
		/*
		//login做什么的？作为跟单人条件进行查询
		//oqm对象中是否有这个设置？
		oqm.setCompleter(login);
		//只显示当前登陆人对应的任务信息
		//BUG:如果此处显示的采购销售都有的话，那么此处使用in来做
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		return orderDao.getAll(oqm, pageNum, pageCount);
		*/
		//login做什么的？作为跟单人条件进行查询
		//oqm对象中是否有这个设置？
		oqm.setCompleter(login);
		return orderDao.getAll(oqm, pageNum, pageCount,taskTypes);
	}

	public void endTask(Long uuid) {
		//使用快照更新状态
		OrderModel om = orderDao.get(uuid);
		//逻辑校验
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)){
			throw new AppException("请不要进行非法操作！");
		}
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,	Integer pageCount) {
		//将要获取的数据对应的条件设置到oqm中
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public int getInStoreCount(OrderQueryModel oqm) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getCount(oqm);
	}
}








