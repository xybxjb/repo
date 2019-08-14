package cn.deepcoding.service;


import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;


public interface ProxyTecherStudentService {

	public PageData find(Integer id,String year,String month,Page page);

	public Student get(Integer id);

	public void save(ProxyTeaMonth proxyTeaMonth);

}
