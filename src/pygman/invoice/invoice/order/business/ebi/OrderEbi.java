package pygman.invoice.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel> {

	public void save(OrderModel om, Long[] goodsUuids, Integer[] nums, Double[] prices, EmpModel creater);
	/**
	 * 对指定uuid的订单审核通过
	 * @param uuid	本次审核的订单uuid
	 * @param empModel 审核人
	 */
	public void buyCheckPass(Long uuid, EmpModel empModel);
	public void buyCheckNoPass(Long uuid, EmpModel login);
	public Integer getTransportCount(OrderQueryModel oqm);
	public List<OrderModel> getAllTransport(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	/**
	 * 为订单指定跟单人
	 * @param orderUuid 订单uuid
	 * @param completerUuid 跟单人uuid
	 */
	public void task(Long orderUuid, Long completerUuid);
	public int getTasksCount(OrderQueryModel oqm, EmpModel login);
	public List<OrderModel> getAllTasks(OrderQueryModel oqm, Integer pageNum,
										Integer pageCount, EmpModel login);
	/**
	 * 结单
	 * @param uuid 订单uuid
	 */
	public void endTask(Long uuid);
	/**
	 * 获取所有的待入库订单信息
	 * @param pageCount 
	 * @param pageNum 
	 * @param oqm 
	 * @return
	 */
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	public int getInStoreCount(OrderQueryModel oqm);

}
