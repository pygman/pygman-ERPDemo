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

public class RoleAction extends BaseAction {
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

	public String list() {
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm, pageNum, pageCount);
		put("roleList", roleList);
		return LIST;
	}

	public String input() {
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList", menuList);
		List<ResModel> resList = resEbi.getAll();
		put("resList", resList);
		if (rm.getUuid() != null) {
			rm = roleEbi.get(rm.getUuid());
			resUuids = new Long[rm.getReses().size()];
			int i = 0;
			for (ResModel temp : rm.getReses()) {
				resUuids[i++] = temp.getUuid();
			}
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for (MenuModel temp : rm.getMenus()) {
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] resUuids;
	public Long[] menuUuids;

	public String save() {
		if (rm.getUuid() == null) {
			roleEbi.save(rm, resUuids, menuUuids);
		} else {
			roleEbi.update(rm, resUuids, menuUuids);
		}
		return TO_LIST;
	}

	public String delete() {
		roleEbi.delete(rm);
		return TO_LIST;
	}
}
