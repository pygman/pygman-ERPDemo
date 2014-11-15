package pygman.invoice.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel> {
	/**
	 * 入库部分商品
	 * @param num 入库数量
	 * @param storeUuid 仓库uuid
	 * @param odmUuid 订单明细uuid
	 * @param login 入库人
	 */
	public OrderDetailModel inGoods(Integer num, Long storeUuid, Long odmUuid, EmpModel login);

}
