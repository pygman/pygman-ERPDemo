package pygman.invoice.invoice.storeModel.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.storeModel.vo.StoreModelModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface StoreModelEbi extends BaseEbi<StoreModelModel>{

}
