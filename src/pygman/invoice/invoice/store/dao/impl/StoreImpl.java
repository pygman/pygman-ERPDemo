package pygman.invoice.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import pygman.invoice.invoice.store.dao.dao.StoreDao;
import pygman.invoice.invoice.store.vo.StoreModel;
import pygman.invoice.invoice.store.vo.StoreQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class StoreImpl extends BaseDaoImpl<StoreModel> implements StoreDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreQueryModel sqm = (StoreQueryModel)qm;
		//TODO 添加自定义查询条件
	}

}
