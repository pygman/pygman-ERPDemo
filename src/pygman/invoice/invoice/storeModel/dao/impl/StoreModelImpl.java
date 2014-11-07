package pygman.invoice.invoice.storeModel.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.storeModel.dao.dao.StoreModelDao;
import pygman.invoice.invoice.storeModel.vo.StoreModelModel;
import pygman.invoice.invoice.storeModel.vo.StoreModelQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class StoreModelImpl extends BaseDaoImpl<StoreModelModel> implements StoreModelDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreModelQueryModel sqm = (StoreModelQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
