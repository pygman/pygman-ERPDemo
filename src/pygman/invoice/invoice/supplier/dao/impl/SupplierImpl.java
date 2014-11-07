package pygman.invoice.invoice.supplier.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.supplier.dao.dao.SupplierDao;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class SupplierImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		SupplierQueryModel sqm = (SupplierQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
