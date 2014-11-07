package pygman.invoice.invoice.orderDetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.orderDetail.dao.dao.OrderDetailDao;
import pygman.invoice.invoice.orderDetail.vo.OrderDetailModel;
import pygman.invoice.invoice.orderDetail.vo.OrderDetailQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class OrderDetailImpl extends BaseDaoImpl<OrderDetailModel> implements OrderDetailDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		OrderDetailQueryModel oqm = (OrderDetailQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
