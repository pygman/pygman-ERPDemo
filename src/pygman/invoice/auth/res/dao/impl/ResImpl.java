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

public class ResImpl extends BaseDaoImpl<ResModel> implements ResDao{

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		ResQueryModel rqm = (ResQueryModel)qm;
		//TODO 添加自定义查询条件
	}
	
	public List<ResModel> getAllByEmpUuid(Long uuid) {
		//根据uuid获取用户对应的所有资源
		//HQl
		/*
		查询的是资源，但是需要使用资源关联角色
		需要使用角色关联员工
		最后设置的条件是关联的关联的uuid = 指定值
		*/
		/*
		资源->角色->员工(uuid)
		
		select * from tbl_res res,tbl_role role,tbl_role_res rr
		where res.uuid = rr.resUuid and role.uuid = rr.roleUuid
		
		100个员工
		*/
		/*
		A表与B表关联
		查询数据，只查询A与B的关联数据，其他数据不要
		内连接:内连接查询的结果是你关联的表的模型  A,B
		from A join B 
		*/
		/*
		from ResModel rm join rm.roles
		from RoleModel rm join rm.emps
		
		from ResModel res join res.roles role join role.emps
		*/
		//			 select res from EmpModel em join em.roles role join role.reses res where em.uuid = ?
		String hql = "select distinct res from ResModel res join res.roles role join role.emps em where em.uuid= ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-res.xml");
		ResDao dao = (ResDao) ctx.getBean("resDao");
		System.out.println(dao.getAllByEmpUuid(5L));
	}

	public List<String> getResesEmpUuid(Long uuid) {
		String hql = "select res.url from ResModel res join res.roles role join role.emps em where em.uuid= ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}
}
