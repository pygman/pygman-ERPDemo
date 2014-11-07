package @package_addr@.@small@.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import @package_addr@.@small@.dao.dao.@big@Dao;
import @package_addr@.@small@.vo.@big@Model;
import @package_addr@.@small@.vo.@big@QueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;	

public class @big@Impl extends BaseDaoImpl<@big@Model> implements @big@Dao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		@big@QueryModel @little@qm = (@big@QueryModel)qm;
		//TODO 添加自定义查询条件
	}

}
