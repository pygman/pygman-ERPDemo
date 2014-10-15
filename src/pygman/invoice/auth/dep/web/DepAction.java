package pygman.invoice.auth.dep.web;

import java.util.List;

import pygman.invoice.auth.dep.business.ebi.DepEbi;
import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.auth.dep.vo.DepQueryModel;
import pygman.invoice.util.base.BaseAction;

public class DepAction extends BaseAction{
	public DepModel dm = new DepModel();
	//声明查询Model的变量
	public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	public String list(){
		//计算当前最大页码值：需要数据总量
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
	//跳转到添加或修改页面
	public String input(){
		if(dm.getUuid() != null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	//保存或修改
	public String save(){
		//将页面收集的数据传递要业务层进行持久化
		//1.页面收集的数据在哪里？dm
		if(dm.getUuid() == null){
			//保存
			depEbi.save(dm);
		}else{
			//修改
			depEbi.update(dm);
		}
		//2.跳转页面？列表页
		return TO_LIST;
	}
	
	//删除
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
}



