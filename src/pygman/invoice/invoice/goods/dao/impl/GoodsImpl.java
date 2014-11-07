package pygman.invoice.invoice.goods.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.goods.dao.dao.GoodsDao;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class GoodsImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
