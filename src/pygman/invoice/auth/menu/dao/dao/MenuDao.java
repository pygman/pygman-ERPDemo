package pygman.invoice.auth.menu.dao.dao;

import java.util.List;

import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel>{

	public List<MenuModel> getAllParentMenu();

	public List<MenuModel> getAllByEmpUuidAndPuuidIsOne(Long uuid);

	public List<MenuModel> getAllMenuByEmpUuidAndPuuid(Long empUuid,
			Long menuUuid);

}
