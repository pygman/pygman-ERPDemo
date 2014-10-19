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

public class RoleEbo implements RoleEbi {
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

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return roleDao.getAll(qm, pageNum, pageCount);
	}

	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for (Long uuid : resUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for (Long uuid : menuUuids) {
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		roleDao.save(rm);
	}

	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for (Long uuid : resUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);

		Set<MenuModel> menus = new HashSet<MenuModel>();
		for (Long uuid : menuUuids) {
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);

		roleDao.update(rm);
	}

}
