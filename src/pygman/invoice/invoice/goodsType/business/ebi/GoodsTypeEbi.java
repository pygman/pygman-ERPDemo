package pygman.invoice.invoice.goodsType.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.invoice.goodsType.vo.GoodsTypeModel;
import cn.itcast.invoice.util.base.BaseEbi;
@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel>{

}
