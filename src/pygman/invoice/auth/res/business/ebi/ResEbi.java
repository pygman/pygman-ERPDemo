package pygman.invoice.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface ResEbi extends BaseEbi<ResModel>{
	/**
	 * 根据员工uuid获取可操作的资源列表
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<ResModel> getAllByEmpUuid(Long uuid);
	/**
	 * 获取指定用户对应的所有可操作资源
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<String> getResesEmpUuid(Long uuid);

}
