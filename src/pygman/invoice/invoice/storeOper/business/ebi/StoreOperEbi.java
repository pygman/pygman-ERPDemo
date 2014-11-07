package pygman.invoice.invoice.storeOper.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.storeOper.vo.StoreOperModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface StoreOperEbi extends BaseEbi<StoreOperModel>{

}
