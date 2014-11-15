package pygman.invoice.invoice.goodstype.business.ebo;

import pygman.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import pygman.invoice.invoice.goodstype.dao.dao.GoodsTypeDao;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class GoodsTypeEbo implements GoodsTypeEbi {
	private GoodsTypeDao goodsTypeDao;
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}

	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}

	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}

	public GoodsTypeModel get(Serializable uuid) {
		return goodsTypeDao.get(uuid);
	}

	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsTypeDao.getCount(qm);
	}

	public List<GoodsTypeModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsTypeDao.getAll(qm,pageNum,pageCount);
	}

	public List<GoodsTypeModel> getAllBySupplierUuid(Long supplierUuid) {
		return goodsTypeDao.getAllBySupplierUuid(supplierUuid);
	}

	public List<GoodsTypeModel> getAllUnionBySupplierUuid(Long uuid) {
		return goodsTypeDao.getAllUnionBySupplierUuid(uuid);
	}

}
