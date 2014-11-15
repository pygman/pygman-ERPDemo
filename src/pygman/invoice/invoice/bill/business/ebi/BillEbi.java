package pygman.invoice.invoice.bill.business.ebi;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface BillEbi {

	public List<Object[]> getBills(BillQueryModel bqm);

	public List<OrderDetailModel> getAllBillDetail(BillQueryModel bqm);

	public void writeBillPie(BillQueryModel bqm, OutputStream os);

	public InputStream writeBillToExcel(BillQueryModel bqm)throws Exception;

}
