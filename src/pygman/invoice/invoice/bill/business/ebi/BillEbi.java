package pygman.invoice.invoice.bill.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.bill.vo.BillModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface BillEbi extends BaseEbi<BillModel>{

}
