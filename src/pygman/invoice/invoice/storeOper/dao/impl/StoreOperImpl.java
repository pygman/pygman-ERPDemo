package pygman.invoice.invoice.storeOper.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.storeOper.dao.dao.StoreOperDao;
import pygman.invoice.invoice.storeOper.vo.StoreOperModel;
import pygman.invoice.invoice.storeOper.vo.StoreOperQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class StoreOperImpl extends BaseDaoImpl<StoreOperModel> implements StoreOperDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreOperQueryModel sqm = (StoreOperQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
