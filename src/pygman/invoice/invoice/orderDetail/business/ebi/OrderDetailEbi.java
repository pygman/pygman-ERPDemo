package pygman.invoice.invoice.orderDetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.orderDetail.vo.OrderDetailModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

}
