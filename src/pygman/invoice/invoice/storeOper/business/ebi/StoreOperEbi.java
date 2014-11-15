package pygman.invoice.invoice.storeoper.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.storeoper.vo.StoreOperModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface StoreOperEbi extends BaseEbi<StoreOperModel> {

}
