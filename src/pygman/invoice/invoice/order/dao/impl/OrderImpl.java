package pygman.invoice.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.order.dao.dao.OrderDao;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class OrderImpl extends BaseDaoImpl<OrderModel> implements OrderDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
