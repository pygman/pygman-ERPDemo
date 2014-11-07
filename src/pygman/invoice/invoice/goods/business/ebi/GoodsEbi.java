package pygman.invoice.invoice.goods.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel>{

}
