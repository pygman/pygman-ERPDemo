package pygman.invoice.invoice.orderDetail.web;

import java.util.List;

import pygman.invoice.invoice.orderDetail.business.ebi.OrderDetailEbi;
import pygman.invoice.invoice.orderDetail.vo.OrderDetailModel;
import pygman.invoice.invoice.orderDetail.vo.OrderDetailQueryModel;
import pygman.invoice.util.base.BaseAction;

public class OrderDetailAction extends BaseAction{
	public OrderDetailModel om = new OrderDetailModel();
	public OrderDetailQueryModel oqm = new OrderDetailQueryModel();

	private OrderDetailEbi orderDetailEbi;
	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}

	public String list(){
		setDataTotal(orderDetailEbi.getCount(oqm));
		List<OrderDetailModel> orderDetailList = orderDetailEbi.getAll(oqm,pageNum,pageCount);
		put("orderDetailList", orderDetailList);
		return LIST;
	}

	public String input(){
		if(om.getUuid() != null){
			om = orderDetailEbi.get(om.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(om.getUuid() == null){
			orderDetailEbi.save(om);
		}else{
			orderDetailEbi.update(om);
		}
		return TO_LIST;
	}

	public String delete(){
		orderDetailEbi.delete(om);
		return TO_LIST;
	}
}
