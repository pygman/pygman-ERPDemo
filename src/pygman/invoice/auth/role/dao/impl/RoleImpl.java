package pygman.invoice.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import pygman.invoice.auth.role.dao.dao.RoleDao;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.auth.role.vo.RoleQueryModel;
import pygman.invoice.util.base.BaseDaoImpl;
import pygman.invoice.util.base.BaseQueryModel;

public class RoleImpl extends BaseDaoImpl<RoleModel> implements RoleDao {

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc) {
		RoleQueryModel rqm = (RoleQueryModel) qm;
	}

}
