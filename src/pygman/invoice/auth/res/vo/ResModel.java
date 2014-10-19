package pygman.invoice.auth.res.vo;

import java.util.Set;

import pygman.invoice.auth.role.vo.RoleModel;

public class ResModel {
	private Long uuid;
	private String name;
	private String url;

	private Set<RoleModel> roles;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
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

	@Override
	public String toString() {
		return "ResModel [uuid=" + uuid + ", name=" + name + ", url=" + url
				+ "]";
	}

}
