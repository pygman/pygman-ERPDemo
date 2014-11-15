package pygman.invoice.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import pygman.invoice.invoice.goodstype.dao.dao.GoodsTypeDao;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		//TODO 添加自定义查询条件
	}

	public List<GoodsTypeModel> getAllBySupplierUuid(Long supplierUuid) {
		String hql = "from GoodsTypeModel where sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,supplierUuid);
	}

	public List<GoodsTypeModel> getAllUnionBySupplierUuid(Long uuid) {
		//使用商品类别内连接商品
		String hql = "select distinct gtm from GoodsTypeModel gtm join gtm.goodses where gtm.sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
