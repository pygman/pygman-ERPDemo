package pygman.invoice.invoice.goodsType.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.invoice.goodsType.dao.dao.GoodsTypeDao;
import pygman.invoice.invoice.goodsType.vo.GoodsTypeModel;
import pygman.invoice.invoice.goodsType.vo.GoodsTypeQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class GoodsTypeImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		//TODO 添加自定义查询条�?
	}

}
