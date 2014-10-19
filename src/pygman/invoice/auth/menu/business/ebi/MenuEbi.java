package pygman.invoice.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{

	public List<MenuModel> getAllParentMenu();

	public List<MenuModel> getAllMenu(Long uuid);
	
	public List<MenuModel> getAllMenuByEmpUuidAndPuuid(Long uuid, Long menuUuid);

}
