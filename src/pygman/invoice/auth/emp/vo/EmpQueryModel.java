package pygman.invoice.auth.emp.vo;

import pygman.invoice.util.base.BaseQueryModel;
import pygman.invoice.util.format.FormatUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel{
	private Long lastLoginTime2;

	private String lastLoginTime2View;
	
	public String getLastLoginTime2View() {
		return lastLoginTime2View;
	}

	public Long getLastLoginTime2() {
		return lastLoginTime2;
	}

	public void setLastLoginTime2(Long lastLoginTime2) {
		this.lastLoginTime2 = lastLoginTime2;
		this.lastLoginTime2View = FormatUtil.formatDate(lastLoginTime2);
	}
	
}
