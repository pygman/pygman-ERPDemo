package pygman.invoice.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import pygman.invoice.auth.menu.business.ebi.MenuEbi;
import pygman.invoice.auth.menu.vo.MenuModel;
import pygman.invoice.auth.menu.vo.MenuQueryModel;
import pygman.invoice.util.base.BaseAction;

public class MenuAction extends BaseAction{
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public String list(){
		//加载所有的父菜单
		List<MenuModel> parentList = menuEbi.getAllParentMenu();
		put("parentList",parentList);
		
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
		put("menuList", menuList);
		return LIST;
	}

	public String input(){
		//加载所有的父菜单
		List<MenuModel> menuList = menuEbi.getAllParentMenu();
		put("menuList",menuList);
		if(mm.getUuid() != null){
			mm = menuEbi.get(mm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm);
		}else{
			menuEbi.update(mm);
		}
		return TO_LIST;
	}

	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}
	
	//显示菜单
	public String showMenu() throws IOException{
		//无论是第一次加载，还是点击一级菜单加载，访问的都是该方法
		//该方法应该具有两个功能，1，加载所有的一级菜单，2加载对应一级菜单中的菜单项
		//使用什么来区分究竟是第一次加载，还是加载菜单项？
		//通过请求中携带的名称为root的参数，如果是source则是第一次加载一级菜单，如果不是source则加载的是指定菜单对应的菜单项
		String root = getRequest().getParameter("root");
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		StringBuilder json = new StringBuilder();
		json.append("[");
		List<MenuModel> menuList = null;
		if("source".equals(root)){
			menuList = menuEbi.getAllMenu(getLogin().getUuid());
			for(MenuModel temp:menuList){
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		}else{
			//加载指定菜单的菜单项，哪个菜单？root中的数据就是指定菜单的uuid
			Long menuUuid = new Long(root);
			//获取指定菜单的对应菜单项
			menuList = menuEbi.getAllMenuByEmpUuidAndPuuid(getLogin().getUuid(),menuUuid);
			//根据数据拼写对应的json数据数组
			//json.append("{\"text\":\"菜单名称2\",\"hasChildren\":false,\"classes\":\"file\"},");
			for(MenuModel temp:menuList){
				json.append("{\"text\":\"");
				json.append("<a href='");
				json.append(temp.getUrl());
				json.append("' target='main'>");
				json.append(temp.getName());
				json.append("</a>");
				json.append("\",\"hasChildren\":false,\"classes\":\"file\"},");
			}
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		pw.write(json.toString());
		pw.flush();
		return null;
	}
	
	//显示菜单
	public String showMenu2() throws IOException{
		//1.获取页面传递的参数root
		//2.根据root的值，返回不同的json数组
		
		//返回json数组，json数据
		//action中如何范围json数据？
		//1.获取到响应对象
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		//2.获取响应对象对应的输出流
		PrintWriter pw = response.getWriter();
		//3.组织数据(json数据)
		StringBuilder json = new StringBuilder();
		//需要一个json数组[{},{}]
		//"{\"text\":\"菜单名称\",\"expanded\":true,\"classes\":\"myclass\",\"id\":\"100\",\"hasChildren\":true},"
		//做一级菜单使用
		//json.append("{\"text\":\"菜单名称1\",\"hasChildren\":true,\"classes\":\"folder\"},");
		//做菜单项使用
		//json.append("{\"text\":\"菜单名称2\",\"hasChildren\":false,\"classes\":\"file\"},");
		
		
		json.append("[");
		//取出当前登陆人对应的可以查看的菜单，展示
		//取出当前登陆人对应的所有可操作的MenuModel,然后动态的生成下面的json数据
		List<MenuModel> menuList = menuEbi.getAllMenu(getLogin().getUuid());
		for(MenuModel temp:menuList){
			json.append("{\"text\":\"");
			json.append(temp.getName());
			json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
			json.append(temp.getUuid());
			json.append("\"},");
		}
		//删除多余的,
		json.deleteCharAt(json.length()-1);
		json.append("]");
		//4.将json数据写入流
		pw.write(json.toString());
		//5.刷新流
		pw.flush();
		//6.设置返回值  null  NONE
		return null;
	}
}





