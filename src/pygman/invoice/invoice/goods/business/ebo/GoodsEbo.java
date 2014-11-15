package pygman.invoice.invoice.goods.business.ebo;

import pygman.invoice.invoice.goods.business.ebi.GoodsEbi;
import pygman.invoice.invoice.goods.dao.dao.GoodsDao;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class GoodsEbo implements GoodsEbi {
	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel gm) {
		gm.setUseNum(0);
		goodsDao.save(gm);
	}

	public void update(GoodsModel gm) {
		//快照（自己修改）
		goodsDao.update(gm);
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public GoodsModel get(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsDao.getCount(qm);
	}

	public List<GoodsModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsDao.getAll(qm,pageNum,pageCount);
	}

	public List<GoodsModel> getAllByGtmUuid(Long uuid) {
		return goodsDao.getAllByGtmUuid(uuid);
	}

	public void updateUseNum() {
		goodsDao.updateUseNum();
	}

	public List<Object[]> getStoreWarnInfo() {
		return goodsDao.getStoreWarnInfo();
	}

}
