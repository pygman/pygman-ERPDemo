package pygman.invoice.invoice.order.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface OrderEbi extends BaseEbi<OrderModel>{

}
