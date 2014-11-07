package pygman.invoice.invoice.supplier.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel>{

}
