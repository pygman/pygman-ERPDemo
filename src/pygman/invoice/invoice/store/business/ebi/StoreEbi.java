package pygman.invoice.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.store.vo.StoreModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface StoreEbi extends BaseEbi<StoreModel> {

}
