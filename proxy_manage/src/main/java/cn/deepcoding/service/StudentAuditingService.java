package cn.deepcoding.service;

import cn.deepcoding.model.StudentAuditing;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface StudentAuditingService {
	public StudentAuditing get(Integer id);
	
	public StudentAuditing listAll(StudentAuditing studentAuditing);
	
	public PageData list(StudentAuditing studentAuditing,Page page);

	public String save(StudentAuditing studentAuditing);
}
