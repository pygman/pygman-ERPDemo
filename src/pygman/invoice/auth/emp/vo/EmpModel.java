package pygman.invoice.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.util.format.FormatUtil;

public class EmpModel {
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	
	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";
	
	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	
	static{
		genderMap.put(EMP_GENDER_OF_MAN,EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN,EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	private Long uuid;
		private String userName;
		private String pwd;
		private String name;
		private String email;
		private String tele;
		private Integer gender;
		private String address;
		private Long birthday;
		private Long lastLoginTime;
		private String lastLoginIp;
		private Integer loginTimes;
	
	private String birthdayView;
	private String genderView;
	private String lastLoginTimeView;
			private DepModel dm;
		private Set<RoleModel> roles;
	
		private String allRes;
	
	public String getAllRes() {
		return allRes;
	}

	public void setAllRes(String allRes) {
		this.allRes = allRes;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}

	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}

	public Long getUuid() {
		return uuid;
	}

	
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	public DepModel getDm() {
		return dm;
	}

	public void setDm(DepModel dm) {
		this.dm = dm;
	}

	public String getBirthdayView() {
		return birthdayView;
	}

	public String getGenderView() {
		return genderView;
	}
	
	
}
