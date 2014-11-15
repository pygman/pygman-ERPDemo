package pygman.invoice.invoice.supplier.dao.impl;


import org.hibernate.criterion.DetachedCriteria;
import pygman.invoice.invoice.supplier.dao.dao.SupplierDao;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.invoice.supplier.vo.SupplierQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

import java.util.List;

public class SupplierImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		SupplierQueryModel sqm = (SupplierQueryModel)qm;
		//TODO 添加自定义查询条件
	}

	public List<SupplierModel> getAllUnion() {
		String hql ="select distinct sm from SupplierModel sm join sm.gtms";
		return this.getHibernateTemplate().find(hql);
	}

	public List<SupplierModel> getAllUnionTwo() {
		String hql ="select distinct sm from SupplierModel sm join sm.gtms gtm join gtm.goodses";
		return this.getHibernateTemplate().find(hql);
	}

}
