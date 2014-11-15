package pygman.invoice.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel> {
	/**
	 * 获取指定供应商产品类别全信息
	 * @param supplierUuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySupplierUuid(Long supplierUuid);
	/**
	 * 获取指定供应商具有商品信息的所有商品类别
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionBySupplierUuid(Long uuid);

}
