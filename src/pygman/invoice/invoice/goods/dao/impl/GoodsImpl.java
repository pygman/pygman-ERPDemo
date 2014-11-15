package pygman.invoice.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pygman.invoice.invoice.goods.dao.dao.GoodsDao;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.goods.vo.GoodsQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class GoodsImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao {

	public void doQbc(BaseQueryModel qm,DetachedCriteria dc){
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		if(gqm.getUnit()!=null && gqm.getUnit().trim().length()>0){
			dc.add(Restrictions.eq("unit", gqm.getUnit().trim()));
		}
		if(gqm.getGtm()!=null && gqm.getGtm().getSm()!=null && gqm.getGtm().getSm().getUuid()!=null && gqm.getGtm().getSm().getUuid()!=-1){
			dc.createAlias("gtm", "g");
			dc.createAlias("g.sm", "s");
			dc.add(Restrictions.eq("s.uuid", gqm.getGtm().getSm().getUuid()));
		}
	}

	public List<GoodsModel> getAllByGtmUuid(Long uuid) {
		String hql = "from GoodsModel where gtm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public void updateUseNum() {
		/*
		update
			tbl_goods gm
		set
			useNum = 
		(select 
			count(uuid)
		from 
			tbl_orderDetail
		where 
			goodsUuid = gm.uuid
		group by
			goodsUuid
		)
		*/
		String hql = "update GoodsModel gm set gm.useNum = (select count(odm.uuid) from OrderDetailModel odm where odm.gm.uuid = gm.uuid group by gm.uuid )";
		this.getHibernateTemplate().bulkUpdate(hql);
	}

	public List<Object[]> getStoreWarnInfo() {
		/*
		select
			gm.name,
			sum(sdm.num) > gm.maxNum,
			sum(sdm.num) < gm.minNum
		from 
			tbl_goods gm,
			tbl_storeDetail sdm
		where
			gm.uuid = sdm.goodsUuid
		group by
			sdm.goodsUuid
		*/
		//hibernate是一个ORM框架，O对象R数据库表之间产生映射
		//映射通过hbm.xml文件完成
		//在映射文件中配置有多个property，描述的是映射关系中的属性与字段间的对应关系
		//执行select语句时，查询的内容，要求必须是映射中出现的关系或者聚合的字段
		//如果出现了属性的操作，则无法被映射
		
		
		//String hql = "select gm.name,sum(sdm.num) > gm.maxNum,sum(sdm.num) < gm.minNum from GoodsModel gm, StoreDetailModel sdm where gm.uuid = sdm.gm.uuid group by sdm.gm.uuid";
		//String hql = "select gm.name,sum(sdm.num) , gm.maxNum, gm.minNum from GoodsModel gm, StoreDetailModel sdm where gm.uuid = sdm.gm.uuid group by sdm.gm.uuid";
		//String hql = "select gm.name,sum(sdm.num) , gm.maxNum- gm.minNum from GoodsModel gm, StoreDetailModel sdm where gm.uuid = sdm.gm.uuid group by sdm.gm.uuid";
		//return this.getHibernateTemplate().find(hql);
		
		//使用Hibernate执行原生SQL的形式执行该操作对应的查询
		String sql = "select gm.name,sum(sdm.num) > gm.maxNum,sum(sdm.num) < gm.minNum from tbl_goods gm, tbl_storeDetail sdm where gm.uuid = sdm.goodsUuid	group by sdm.goodsUuid";
		//使用SQLQuery对象
		Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		//Session s = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery query = s.createSQLQuery(sql); 
		return query.list();
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-goods.xml");
		GoodsDao dao = (GoodsDao) ctx.getBean("goodsDao");
		List<Object[]> temp = dao.getStoreWarnInfo();
		for(Object[] objs:temp){
			for(Object obj:objs){
				System.out.println(obj);
			}
			System.out.println("------------");
		}
	}
	
}
/*
1.划分学科		Java,HTML(略)JS（重点）。。。。。。
2.知识点罗列		Java基础语法，面向对象，API，集合，IO。。。。。。
3.知识点细述		Java基础：JDK配置，数据类型，运算符，表达式，if,for,while,switch......
4.做复习表格		
*/







