package cn.deepcoding.service;

import java.util.List;
import java.util.Map;

import cn.deepcoding.model.StudentProxyTeacher;

public interface StudentProxyTeacherService {

	Map<String,Object> getAll(StudentProxyTeacher studentProxyTeacher,Integer page,Integer rows);

}
