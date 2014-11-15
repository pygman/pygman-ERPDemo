package pygman.invoice.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pygman.invoice.invoice.order.dao.dao.OrderDao;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class OrderImpl extends BaseDaoImpl<OrderModel> implements OrderDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		if(oqm.getCompleter()!=null && oqm.getCompleter().getUuid()!=null){
			dc.add(Restrictions.eq("completer", oqm.getCompleter()));
		}
		if(oqm.getType()!=null && oqm.getType()!=-1){
			dc.add(Restrictions.eq("type",oqm.getType()));
		}
		//TODO 添加自定义查询条件
		
	}
/*
	select...
	from ...
	where
		type = ???
		and 
		type in （？，？，？）
*/	
	private void doQbc(BaseQueryModel qm,DetachedCriteria dc, Integer[] types){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		dc.add(Restrictions.in("type", types));
		doQbc(oqm, dc);
	}
	
	public List<OrderModel> getAll(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] types) {
		//HQL ? QBC?
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		//先在此处固定一个条件，对types使用的条件
		doQbc(oqm,dc,types);
		return this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}

	public Integer getCount(OrderQueryModel oqm, Integer[] types) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc(oqm,dc,types);
		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}

}



















