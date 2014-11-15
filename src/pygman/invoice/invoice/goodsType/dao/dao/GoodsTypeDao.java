package pygman.invoice.invoice.goodstype.dao.dao;

import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.util.base.BaseDao;

import java.util.List;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	public List<GoodsTypeModel> getAllBySupplierUuid(Long supplierUuid);

	public List<GoodsTypeModel> getAllUnionBySupplierUuid(Long uuid);

}
