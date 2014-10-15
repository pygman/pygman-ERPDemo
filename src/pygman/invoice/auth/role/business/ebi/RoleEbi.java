package pygman.invoice.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids);
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids);
}
