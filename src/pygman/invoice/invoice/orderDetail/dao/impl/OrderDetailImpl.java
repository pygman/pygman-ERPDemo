package pygman.invoice.invoice.orderdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import pygman.invoice.invoice.orderdetail.dao.dao.OrderDetailDao;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;


public class OrderDetailImpl extends BaseDaoImpl<OrderDetailModel> implements OrderDetailDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		OrderDetailQueryModel oqm = (OrderDetailQueryModel)qm;
		//TODO 添加自定义查询条件
	}

}
