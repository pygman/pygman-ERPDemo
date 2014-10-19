package pygman.invoice.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel> {
	public List<ResModel> getAllByEmpUuid(Long uuid);

	public List<String> getResesEmpUuid(Long uuid);

}
