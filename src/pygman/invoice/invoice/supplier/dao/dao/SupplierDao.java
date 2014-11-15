package pygman.invoice.invoice.supplier.dao.dao;

import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseDao;

import java.util.List;


public interface SupplierDao extends BaseDao<SupplierModel> {

	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}
