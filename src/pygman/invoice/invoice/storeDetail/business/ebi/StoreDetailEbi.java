package pygman.invoice.invoice.storeDetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.storeDetail.vo.StoreDetailModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}
