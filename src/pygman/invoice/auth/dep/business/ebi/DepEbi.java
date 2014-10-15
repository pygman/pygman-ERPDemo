package pygman.invoice.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface DepEbi extends BaseEbi<DepModel>{
	
}
