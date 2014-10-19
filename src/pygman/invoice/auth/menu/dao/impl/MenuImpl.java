package pygman.invoice.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.auth.menu.dao.dao.MenuDao;
import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.auth.menu.vo.MenuQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class MenuImpl extends BaseDaoImpl<MenuModel> implements MenuDao{
	
	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		MenuQueryModel mqm = (MenuQueryModel)qm;
				dc.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.PARENT_UUID)));
		if(mqm.getName()!=null && mqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		if(mqm.getParent()!=null && mqm.getParent().getUuid()!=null && mqm.getParent().getUuid()!= -1){
			dc.add(Restrictions.eq("parent", mqm.getParent()));
		}
	}

	public List<MenuModel> getAllParentMenu() {
		String hql = "from MenuModel where parent.uuid = ? or uuid = ?";
		return this.getHibernateTemplate().find(hql,MenuModel.PARENT_UUID,MenuModel.PARENT_UUID);
	}
		public List<MenuModel> getAllByEmpUuidAndPuuidIsOne(Long uuid) {
										String hql = "select distinct mm from MenuModel mm join mm.roles role join role.emps em where mm.parent.uuid = ? and em.uuid = ? order by mm.uuid";
		return this.getHibernateTemplate().find(hql,MenuModel.PARENT_UUID,uuid);
	}

	public List<MenuModel> getAllMenuByEmpUuidAndPuuid(Long empUuid,Long menuUuid) {
		String hql = "select distinct mm from MenuModel mm join mm.roles role join role.emps em where mm.parent.uuid = ? and em.uuid = ? order by mm.uuid";
		return this.getHibernateTemplate().find(hql,menuUuid,empUuid);
	}

}
