package pygman.invoice.invoice.bill.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.bill.dao.dao.BillDao;
import pygman.invoice.invoice.bill.vo.BillModel;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class BillImpl extends BaseDaoImpl<BillModel> implements BillDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		BillQueryModel bqm = (BillQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
