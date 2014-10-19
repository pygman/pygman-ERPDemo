package pygman.invoice.auth.res.web;

import java.util.List;

import pygman.invoice.auth.res.business.ebi.ResEbi;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.auth.res.vo.ResQueryModel;
import pygman.invoice.util.base.BaseAction;

public class ResAction extends BaseAction {
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public String list() {
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm, pageNum, pageCount);
		put("resList", resList);
		return LIST;
	}

	public String input() {
		if (rm.getUuid() != null) {
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	public String save() {
		if (rm.getUuid() == null) {
			resEbi.save(rm);
		} else {
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	public String delete() {
		resEbi.delete(rm);
		return TO_LIST;
	}
}
