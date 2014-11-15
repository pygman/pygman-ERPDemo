package pygman.invoice.invoice.supplier.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseEbi;

import java.util.List;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {
	/**
	 * 获取具有商品类别的供应商全信息
	 * @return
	 */
	public List<SupplierModel> getAllUnion();
	/**
	 * 获取具有商品信息的供应商全信息
	 * @return
	 */
	public List<SupplierModel> getAllUnionTwo();

}
