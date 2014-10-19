package pygman.invoice.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pygman.invoice.auth.res.dao.dao.ResDao;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.auth.res.vo.ResQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class ResImpl extends BaseDaoImpl<ResModel> implements ResDao {

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc) {
		ResQueryModel rqm = (ResQueryModel) qm;
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		String hql = "select distinct res from ResModel res join res.roles role join role.emps em where em.uuid= ?";
		return this.getHibernateTemplate().find(hql, uuid);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml", "applicationContext-res.xml");
		ResDao dao = (ResDao) ctx.getBean("resDao");
		System.out.println(dao.getAllByEmpUuid(5L));
	}

	public List<String> getResesEmpUuid(Long uuid) {
		String hql = "select res.url from ResModel res join res.roles role join role.emps em where em.uuid= ?";
		return this.getHibernateTemplate().find(hql, uuid);
	}
}
