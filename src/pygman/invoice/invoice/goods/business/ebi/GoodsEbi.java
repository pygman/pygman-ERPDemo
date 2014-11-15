package pygman.invoice.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {
	/**
	 * 获取指定商品类别对应的所有商品信息
	 * @param uuid 商品类别uuid
	 * @return
	 */
	public List<GoodsModel> getAllByGtmUuid(Long uuid);
	/**
	 * 定时器维护商品使用频度
	 */
	public void updateUseNum();
	/**
	 * 获取库存预警信息
	 * 数据格式：商品名，是否超过最大值，是否低于最小值
	 * @return
	 */
	public List<Object[]> getStoreWarnInfo();

}
