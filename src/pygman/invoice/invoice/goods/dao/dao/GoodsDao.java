package pygman.invoice.invoice.goods.dao.dao;

import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.util.base.BaseDao;

import java.util.List;


public interface GoodsDao extends BaseDao<GoodsModel> {

	public List<GoodsModel> getAllByGtmUuid(Long uuid);

	public void updateUseNum();

	public List<Object[]> getStoreWarnInfo();

}
