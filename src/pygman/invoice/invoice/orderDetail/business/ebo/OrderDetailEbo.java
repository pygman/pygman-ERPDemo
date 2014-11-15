package pygman.invoice.invoice.orderdetail.business.ebo;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.orderdetail.business.ebi.OrderDetailEbi;
import pygman.invoice.invoice.orderdetail.dao.dao.OrderDetailDao;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.invoice.store.vo.StoreModel;
import pygman.invoice.invoice.storedetail.dao.dao.StoreDetailDao;
import pygman.invoice.invoice.storedetail.vo.StoreDetailModel;
import pygman.invoice.invoice.storeoper.dao.dao.StoreOperDao;
import pygman.invoice.invoice.storeoper.vo.StoreOperModel;
import pygman.invoice.util.base.BaseQueryModel;
import pygman.invoice.util.exception.AppException;

import java.io.Serializable;
import java.util.List;

public class OrderDetailEbo implements OrderDetailEbi {
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private StoreOperDao storeOperDao;
	
	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void save(OrderDetailModel om) {
		orderDetailDao.save(om);
	}

	public void update(OrderDetailModel om) {
		orderDetailDao.update(om);
	}

	public void delete(OrderDetailModel om) {
		orderDetailDao.delete(om);
	}

	public OrderDetailModel get(Serializable uuid) {
		return orderDetailDao.get(uuid);
	}

	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDetailDao.getCount(qm);
	}

	public List<OrderDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDetailDao.getAll(qm,pageNum,pageCount);
	}

	public OrderDetailModel inGoods(Integer num, Long storeUuid, Long odmUuid,EmpModel login) {
		//1.订单明细中的剩余数量要改(快照)
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		OrderModel om = odm.getOm();
		
		//逻辑校验
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		//逻辑校验
		if(odm.getSurplus()<num){
			throw new AppException("对不起，没有这么多货物！");
		}
		//设置订单明细中货物的剩余数量为，当前数量减本次入库量
		odm.setSurplus(odm.getSurplus()-num);
		
		//2.仓库中的商品数量要改
		//初始化仓库对象
		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);
		
		//如果现在这个货物在仓库中有，执行的是数量update,如果没有，我应该添加一个新记录
		//2.1先判断该货物对应的仓库中是否有记录
		StoreDetailModel sdm = storeDetailDao.getByStoreAndGoods(storeUuid,odm.getGm().getUuid());
		if(sdm == null){
			//如果没有记录，那么是新建记录
			sdm =  new StoreDetailModel();
			sdm.setSm(sm);
			sdm.setGm(odm.getGm());
			sdm.setNum(num);
			storeDetailDao.save(sdm);
		}else{
			//如果有记录，那么修改数量
			//快照更新
			sdm.setNum(sdm.getNum()+num);
		}
		
		//3.入库记录要记
		StoreOperModel som = new StoreOperModel();
		som.setNum(num);
		//设置操作时间为当前系统时间
		som.setOperTime(System.currentTimeMillis());
		//设置当前操作为入库
		som.setType(StoreOperModel.STOREOPER_TYPE_OF_IN);
		som.setEm(login);
		som.setSm(sm);
		som.setGm(odm.getGm());
		som.setOdm(odm);
		storeOperDao.save(som);
		//4.如果所有订单明细都入完了，修改订单状态，修改结单时间
		//判断
		//获取订单中的所有明细，将剩余量相加，如果==0，结单
		
		int sum = 0;
		for(OrderDetailModel temp :om.getOdms()){
			sum+= temp.getSurplus();
		}
		if(sum == 0){
			om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			om.setCompleteTime(System.currentTimeMillis());
		}
		return odm;
	}

}









