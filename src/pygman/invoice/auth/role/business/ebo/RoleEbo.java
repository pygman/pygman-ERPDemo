package pygman.invoice.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.auth.role.business.ebi.RoleEbi;
import pygman.invoice.auth.role.dao.dao.RoleDao;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void save(RoleModel rm) {
		roleDao.save(rm);
	}

	public void update(RoleModel rm) {
		roleDao.update(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		//保存角色时，关联资源数据
		//当前的rm是从页面收集来的，里面只有name,code两个属性
		//将resUuids中的数据变为rm的关系数据
		//需要将保存资源uuid的数组中的数据，转化成可用的集合数据，然后设置给rm对象
		//arr->set
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			//uuid->model
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		//menuUuids
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		//调用保存操作
		roleDao.save(rm);
	}
	//暂定
	//1.提交的name,code,隐藏的uuid
	//2.resUuids本次提交的所有的资源关系
	//3.有没有角色对员工的数据？
	//4.rm对象中的emps对象是?null
	//5.h3对关联数据的判定依据是，如果关系数据为null表名断开关系
	//解决方案：
	//A:使用快照更新
	//B:使角色放弃对员工的关系维护:设置关系中的inverse=true
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			//uuid->model
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			//uuid->model
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.update(rm);
	}
	//关联关系维护为什么是删除+添加而不是修改
	//原始具有关系3条，修改后变为1条
	//原始具有关系1条，修改后变为3条
	//删除原始的所有关系，创建全新的关系
	
}
