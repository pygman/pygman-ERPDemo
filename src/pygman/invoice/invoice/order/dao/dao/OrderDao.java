package pygman.invoice.invoice.order.dao.dao;

import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.invoice.order.vo.OrderQueryModel;
import pygman.invoice.util.base.BaseDao;

import java.util.List;

public interface OrderDao extends BaseDao<OrderModel> {

	public List<OrderModel> getAll(OrderQueryModel oqm, Integer pageNum, Integer pageCount, Integer[] types);

	public Integer getCount(OrderQueryModel oqm, Integer[] types);

}
