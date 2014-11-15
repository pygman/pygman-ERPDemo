package pygman.invoice.invoice.storedetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import pygman.invoice.invoice.storedetail.vo.StoreDetailModel;
import pygman.invoice.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel> {

}
