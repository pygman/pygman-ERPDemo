package pygman.invoice.invoice.orderdetail.web;

import pygman.invoice.invoice.orderdetail.business.ebi.OrderDetailEbi;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailQueryModel;
import pygman.invoice.util.base.BaseAction;

import java.util.List;

public class OrderDetailAction extends BaseAction {
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
	
	//--ajax--------------------------------
	public OrderDetailModel getOm() {
		return om;
	}
	public String ajaxGetNumAndSurplusByOdmUuid(){
		//通过om.uuid获取订单明细中的两个数据，num,surplus
		om = orderDetailEbi.get(om.getUuid());
		return "ajaxGetNumAndSurplusByOdmUuid";
	}
	
	//入库
	public Integer num;
	public Long storeUuid ;
	public Long odmUuid;
	public String ajaxInGoods(){
		om = orderDetailEbi.inGoods(num,storeUuid,odmUuid,getLogin());
		return "ajaxGetNumAndSurplusByOdmUuid";
	}
	
	
}





