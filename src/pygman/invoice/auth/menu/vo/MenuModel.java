package pygman.invoice.auth.menu.vo;

import java.util.Set;

import pygman.invoice.auth.role.vo.RoleModel;

public class MenuModel {
	public static final Long PARENT_UUID = 1L;

	private Long uuid;
	private String name;
	private String url;
	private MenuModel parent;
	private Set<MenuModel> children;
	private Set<RoleModel> roles;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public Set<MenuModel> getChildren() {
		return children;
	}

	public void setChildren(Set<MenuModel> children) {
		this.children = children;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuModel getParent() {
		return parent;
	}

	public void setParent(MenuModel parent) {
		this.parent = parent;
	}

}
