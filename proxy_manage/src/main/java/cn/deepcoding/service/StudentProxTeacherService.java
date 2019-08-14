package cn.deepcoding.service;

import java.util.List;

import cn.deepcoding.model.StudentProxTeacher;
import cn.deepcoding.page.Page;

public interface StudentProxTeacherService {

	Object findAll(Page page, StudentProxTeacher studentProxTeacher);

}
