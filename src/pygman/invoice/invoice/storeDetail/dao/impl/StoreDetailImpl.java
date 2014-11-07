package pygman.invoice.invoice.storeDetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.storeDetail.dao.dao.StoreDetailDao;
import pygman.invoice.invoice.storeDetail.vo.StoreDetailModel;
import pygman.invoice.invoice.storeDetail.vo.StoreDetailQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class StoreDetailImpl extends BaseDaoImpl<StoreDetailModel> implements StoreDetailDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreDetailQueryModel sqm = (StoreDetailQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
