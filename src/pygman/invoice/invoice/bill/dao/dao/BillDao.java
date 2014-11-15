package pygman.invoice.invoice.bill.dao.dao;

import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;

import java.util.List;


public interface BillDao {

	public List<Object[]> getBills(BillQueryModel bqm);

	public List<OrderDetailModel> getAllBillDetail(BillQueryModel bqm);

}
