package pygman.invoice.auth.res.dao.dao;

import java.util.List;

import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.util.base.BaseDao;

public interface ResDao extends BaseDao<ResModel>{

	public List<ResModel> getAllByEmpUuid(Long uuid);

	public List<String> getResesEmpUuid(Long uuid);

}
