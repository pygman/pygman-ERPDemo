package pygman.invoice.invoice.storeoper.dao.impl;


import org.hibernate.criterion.DetachedCriteria;
import pygman.invoice.invoice.storeoper.dao.dao.StoreOperDao;
import pygman.invoice.invoice.storeoper.vo.StoreOperModel;
import pygman.invoice.invoice.storeoper.vo.StoreOperQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class StoreOperImpl extends BaseDaoImpl<StoreOperModel> implements StoreOperDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreOperQueryModel sqm = (StoreOperQueryModel)qm;
		//TODO 添加自定义查询条件
	}

}
