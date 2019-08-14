package cn.deepcoding.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentDormitoryQuerry;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;

public interface DormitoryService {
	// 查询所有
	PageData FindDormitoryAll(Dormitory dormitory, String studentName, Page page);

	// 添加宿舍
	String DormitoryAdd(Dormitory dormitory);

	// 添加宿舍成员，过滤
	List<Student> getstudnt(HttpServletRequest request);

	// 展示宿舍成员详细信息/注意不是宿舍信息
	List<StudentDormitoryQuerry> GetStudentDormitoryById(Dormitory dormitory);

	// 通过ID查询宿舍信息
	Dormitory GetById(Dormitory dormitory);

	// 修改宿舍信息
	String DormitoryUpdate(Dormitory dormitory);

	// 根据宿舍ID删除屏蔽宿舍信息
	void deleteDormitory(Integer id);

	// 获取所有的宿舍号接口
	List<Dormitory> listAllnumber();
}
