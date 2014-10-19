package pygman.invoice.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import pygman.invoice.auth.emp.dao.dao.EmpDao;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.auth.emp.vo.EmpQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class EmpImpl extends BaseDaoImpl<EmpModel> implements EmpDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		EmpQueryModel eqm = (EmpQueryModel)qm;
		
		if(eqm.getUserName()!=null && eqm.getUserName().trim().length()>0){
			dc.add(Restrictions.eq("userName", eqm.getUserName().trim()));
		}
		if(eqm.getName()!=null && eqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+eqm.getName().trim()+"%"));
		}
		if(eqm.getTele()!=null && eqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		}
		if(eqm.getGender()!=null && eqm.getGender()!= -1){
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		}
		if(eqm.getEmail()!=null && eqm.getEmail().trim().length()>0){
			dc.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		}
		if(eqm.getLastLoginTime()!=null){
			dc.add(Restrictions.ge("lastLoginTime", eqm.getLastLoginTime()));
		}
						if(eqm.getLastLoginTime2()!=null){
			dc.add(Restrictions.le("lastLoginTime", eqm.getLastLoginTime2()+24*60*60*1000-1));
		}
		if(eqm.getDm()!=null && eqm.getDm().getUuid()!=null && eqm.getDm().getUuid()!= -1){
						dc.createAlias("dm", "d");
			dc.add(Restrictions.eq("d.uuid", eqm.getDm().getUuid()));
		}
	}
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql = "from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> empList = this.getHibernateTemplate().find(hql,userName,pwd);
		return empList.size() > 0 ? empList.get(0) : null;
	}

	public boolean updatePwd(String userName, String pwd, String newPwd) {
				String hql = "update EmpModel set pwd = ? where userName = ? and pwd = ?";
				int upd = this.getHibernateTemplate().bulkUpdate(hql,newPwd,userName,pwd);
		return upd > 0;
	}

	public List<EmpModel> getAllByDep(Long depUuid) {
		String hql = "from EmpModel where dm.uuid = ?";
		return this.getHibernateTemplate().find(hql,depUuid);
	}
}
