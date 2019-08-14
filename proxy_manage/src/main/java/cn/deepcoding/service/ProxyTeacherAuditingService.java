package cn.deepcoding.service;


import java.util.Date;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherExpense;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface ProxyTeacherAuditingService {
	
	public PageData ListAll(String name, String tel, String idCard, Date starttime, Date endtime, Page page);

	public ProxyTeacherExpense getById(Integer id);

	public void saveOne(Integer id, String auditState,String auditTime);

	public void saveTwo(Integer id, String auditState, String auditor, String auditTime);
	
	public PageData waitToSubmit(String name, String tel, String idCard, Date starttime, Date endtime, Page page);

	public ProxyTeacherExpense getByIdWait(Integer id);

	public void update(Integer id, String auditor, String auditTime, String auditState);

	public PageData haveToSubmit(String name, String tel, String idCard, Date starttime, Date endtime, Page page);
	
	public ProxyTeacher findTeacherId(Integer id);

	public ProxyTeacherExpense getBySubmitId(Integer id);

}
