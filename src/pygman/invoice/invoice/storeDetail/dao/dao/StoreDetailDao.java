package pygman.invoice.invoice.storedetail.dao.dao;


import pygman.invoice.invoice.storedetail.vo.StoreDetailModel;
import pygman.invoice.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {

	public StoreDetailModel getByStoreAndGoods(Long storeUuid, Long uuid);

}
