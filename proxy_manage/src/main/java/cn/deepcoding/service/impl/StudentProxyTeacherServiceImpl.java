package cn.deepcoding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.StudentProxyTeacherDao;
import cn.deepcoding.model.StudentProxyTeacher;
import cn.deepcoding.service.StudentProxyTeacherService;
@Service
@Transactional
public class StudentProxyTeacherServiceImpl implements StudentProxyTeacherService{
	@Autowired
	private StudentProxyTeacherDao studentProxyTeacherDao;
	@Override
	public Map<String,Object> getAll(StudentProxyTeacher studentProxyTeacher,Integer page,Integer rows) {
		// TODO Auto-generated method stub
		Integer start = (page-1)*rows;
		List<StudentProxyTeacher> list = studentProxyTeacherDao.getAll(studentProxyTeacher,start,rows);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", rows);
		map.put("rows", list);
		return map;
	}

}
