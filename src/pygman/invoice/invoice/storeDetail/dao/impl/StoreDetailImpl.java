package pygman.invoice.invoice.storedetail.dao.impl;


import org.hibernate.criterion.DetachedCriteria;
import pygman.invoice.invoice.storedetail.dao.dao.StoreDetailDao;
import pygman.invoice.invoice.storedetail.vo.StoreDetailModel;
import pygman.invoice.invoice.storedetail.vo.StoreDetailQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

import java.util.List;

public class StoreDetailImpl extends BaseDaoImpl<StoreDetailModel> implements StoreDetailDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		StoreDetailQueryModel sqm = (StoreDetailQueryModel)qm;
		//TODO 添加自定义查询条件
	}

	public StoreDetailModel getByStoreAndGoods(Long storeUuid, Long uuid) {
		String hql ="from StoreDetailModel where sm.uuid = ? and gm.uuid = ?";
		List<StoreDetailModel> temp = this.getHibernateTemplate().find(hql,storeUuid,uuid);
		return temp.size()>0?temp.get(0):null;
	}

}
