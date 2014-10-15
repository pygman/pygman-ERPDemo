package pygman.invoice.auth.dep.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.auth.dep.dao.dao.DepDao;
import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.auth.dep.vo.DepQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class DepImpl extends BaseDaoImpl<DepModel> implements DepDao{
	
	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		DepQueryModel dqm = (DepQueryModel)qm;
		//TODO 添加自定义查询条件
		if(dqm.getName()!=null && dqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
	}
	
}
