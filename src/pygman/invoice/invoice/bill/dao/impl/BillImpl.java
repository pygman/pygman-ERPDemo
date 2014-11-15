package pygman.invoice.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import pygman.invoice.invoice.bill.dao.dao.BillDao;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;


public class BillImpl extends HibernateDaoSupport implements BillDao {

	public List<Object[]> getBills(BillQueryModel bqm) {
		/*
		select 
			gm.uuid,gm.name,sum(num)			->gm,sum(num)
		from 
			tbl_orderDetail odm,
			tbl_goods gm
		where
			gm.uuid = goodsUuid
		group by
			goodsUuid
		 */
		//select name,age,address from ....			->Object[]		obj[0] name obj[1] age obj[2] address
		//select name,address,age from ....			->Object[]		obj[0] name obj[1] address	obj[2] age
		/*
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.property("name"));
		plist.add(Projections.property("address"));
		plist.add(Projections.property("age"));
		dc.setProjection(plist);
		*/
		
		//查询出报表的数据,依赖bqm条件
		//QBC
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		//如何设置QBC查询的查询内容为多个，需要设置投影
		//分组的设置由投影进行
		ProjectionList plist = Projections.projectionList();
		//设置分组为gm分组，默认按照gm的OID分组
		plist.add(Projections.groupProperty("gm"));
		plist.add(Projections.sum("num"));
		dc.setProjection(plist);
		
		//设置条件
		doQbc(bqm, dc);
		
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	private void doQbc(BillQueryModel bqm,DetachedCriteria dc){
		dc.createAlias("om", "o");
		if(bqm.getStart()!=null){
			dc.add(Restrictions.ge("o.createTime", bqm.getStart()));
		}
		if(bqm.getEnd()!=null){
			dc.add(Restrictions.le("o.createTime", bqm.getEnd()));
		}
		if(bqm.getType()!=null && bqm.getType()!= -1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid() != -1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-bill.xml");
		BillDao dao = (BillDao) ctx.getBean("billDao");
		List<Object[]> temp = dao.getBills(new BillQueryModel());
		for(Object[] objs:temp){
			for(Object obj:objs){
				System.out.println(obj.getClass());
			}
			System.out.println("--------------");
		}
	}

	public List<OrderDetailModel> getAllBillDetail(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		if(bqm.getGoodsUuid()!=null ){
			dc.createAlias("gm", "g");
			dc.add(Restrictions.eq("g.uuid", bqm.getGoodsUuid()));
		}
		doQbc(bqm, dc);
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
}
