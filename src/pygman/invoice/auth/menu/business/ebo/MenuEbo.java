package pygman.invoice.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.auth.menu.business.ebi.MenuEbi;
import pygman.invoice.auth.menu.dao.dao.MenuDao;
import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void update(MenuModel mm) {
		menuDao.update(mm);
	}

	public void delete(MenuModel mm) {
		//加载关系
		mm = menuDao.get(mm.getUuid());
		menuDao.delete(mm);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public List<MenuModel> getAllParentMenu() {
		return menuDao.getAllParentMenu();
	}

	public List<MenuModel> getAllMenu(Long uuid) {
		return menuDao.getAllMenuByEmpUuidAndPuuid(uuid,MenuModel.PARENT_UUID);
	}

	public List<MenuModel> getAllMenuByEmpUuidAndPuuid(Long empUuid, Long menuUuid) {
		return menuDao.getAllMenuByEmpUuidAndPuuid(empUuid,menuUuid);
	}

}
