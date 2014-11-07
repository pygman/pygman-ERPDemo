package pygman.invoice.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.invoice.order.business.ebi.OrderEbi;
import pygman.invoice.invoice.order.dao.dao.OrderDao;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.util.base.BaseQueryModel;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

}