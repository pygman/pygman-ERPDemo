package pygman.invoice.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import pygman.invoice.auth.menu.business.ebi.MenuEbi;
import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.auth.menu.vo.MenuQueryModel;
import pygman.invoice.util.base.BaseAction;

public class MenuAction extends BaseAction {
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public String list() {
		List<MenuModel> parentList = menuEbi.getAllParentMenu();
		put("parentList", parentList);

		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm, pageNum, pageCount);
		put("menuList", menuList);
		return LIST;
	}

	public String input() {
		List<MenuModel> menuList = menuEbi.getAllParentMenu();
		put("menuList", menuList);
		if (mm.getUuid() != null) {
			mm = menuEbi.get(mm.getUuid());
		}
		return INPUT;
	}

	public String save() {
		if (mm.getUuid() == null) {
			menuEbi.save(mm);
		} else {
			menuEbi.update(mm);
		}
		return TO_LIST;
	}

	public String delete() {
		menuEbi.delete(mm);
		return TO_LIST;
	}

	public String showMenu() throws IOException {
		String root = getRequest().getParameter("root");
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		StringBuilder json = new StringBuilder();
		json.append("[");
		List<MenuModel> menuList = null;
		if ("source".equals(root)) {
			menuList = menuEbi.getAllMenu(getLogin().getUuid());
			for (MenuModel temp : menuList) {
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		} else {
			Long menuUuid = new Long(root);
			menuList = menuEbi.getAllMenuByEmpUuidAndPuuid(
					getLogin().getUuid(), menuUuid);
			for (MenuModel temp : menuList) {
				json.append("{\"text\":\"");
				json.append("<a href='");
				json.append(temp.getUrl());
				json.append("' target='main'>");
				json.append(temp.getName());
				json.append("</a>");
				json.append("\",\"hasChildren\":false,\"classes\":\"file\"},");
			}
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		pw.write(json.toString());
		pw.flush();
		return null;
	}

	public String showMenu2() throws IOException {

		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		StringBuilder json = new StringBuilder();

		json.append("[");
		List<MenuModel> menuList = menuEbi.getAllMenu(getLogin().getUuid());
		for (MenuModel temp : menuList) {
			json.append("{\"text\":\"");
			json.append(temp.getName());
			json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
			json.append(temp.getUuid());
			json.append("\"},");
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		pw.write(json.toString());
		pw.flush();
		return null;
	}
}
