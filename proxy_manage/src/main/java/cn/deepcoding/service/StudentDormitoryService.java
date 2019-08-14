package cn.deepcoding.service;

import cn.deepcoding.model.StudentDormitory;

public interface StudentDormitoryService {
	// 向宿舍添加成员
	void StudentDormitoryAdd(StudentDormitory studentDormitory);

	// 宿舍人员退宿：不可更改
	void studentexit(StudentDormitory studentDormitory);

	// 宿舍人员移除成员
	void studentremove(StudentDormitory studentDormitory);

	// 设置宿舍长
	void DormitorylleaderSystem(String id, Integer did);
	//通过学生id查看住宿情况
	StudentDormitory getBoarder(Integer id);

	



}
