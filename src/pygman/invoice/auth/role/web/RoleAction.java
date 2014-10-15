package pygman.invoice.auth.role.web;

import java.util.List;

import pygman.invoice.auth.menu.business.ebi.MenuEbi;
import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.auth.res.business.ebi.ResEbi;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.auth.role.business.ebi.RoleEbi;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.auth.role.vo.RoleQueryModel;
import pygman.invoice.util.base.BaseAction;

public class RoleAction extends BaseAction{
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public String list(){
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm,pageNum,pageCount);
		put("roleList", roleList);
		return LIST;
	}
	
	public String input(){
		//需要加载全菜单信息
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList",menuList);
		//需要加载全资源信息
		List<ResModel> resList = resEbi.getAll();
		put("resList",resList);
		if(rm.getUuid() != null){
			rm = roleEbi.get(rm.getUuid());
			//修改时，必须先将当前对象所具有的关系，转换成页面可以接受的数据格式
			//set->arr
			resUuids = new Long[rm.getReses().size()];
			int i = 0;
			for(ResModel temp :rm.getReses()){
				resUuids[i++] = temp.getUuid();
			}
			//set->arr
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for(MenuModel temp:rm.getMenus()){
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}
	//需要定义一个变量收集页面传递过来的资源uuid，该数据是多个
	//struts2中如何收集多个相同名称的表单值?	使用数组，使用集合
	//public List<Long> aa;
	//public Set<Long> aa;
	public Long[] resUuids;
	public Long[] menuUuids;
	//数组的内存结构比集合简单，运行速度快
	public String save(){
		if(rm.getUuid() == null){
			roleEbi.save(rm,resUuids,menuUuids);
		}else{
			roleEbi.update(rm,resUuids,menuUuids);
		}
		return TO_LIST;
	}

	public String delete(){
		roleEbi.delete(rm);
		return TO_LIST;
	}
}
