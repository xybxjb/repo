package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.constants.SexConst;
import cn.deepcoding.dao.DormitoryDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.dao.StudentDormitoryDao;
import cn.deepcoding.model.Dormitory;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentDormitory;
import cn.deepcoding.page.Page;
import cn.deepcoding.service.StudentDormitoryService;

@Service
@Transactional
public class StudentDormitoryServiceImpl implements StudentDormitoryService {
	@Autowired
	private StudentDormitoryDao studentdormitorydao;
	@Autowired
	private StudentDao studentdao;
	@Autowired
	private DormitoryDao doritorydao;
	// 向宿舍添加学生
	@Override
	public void StudentDormitoryAdd(StudentDormitory studentDormitory) {
		// TODO Auto-generated method stub
		studentDormitory.setDormitoryleader(0);
		Dormitory dormitory = new Dormitory();
		dormitory.setId(studentDormitory.getDormitroy().getId());
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		page.setStart(0);
		List<Dormitory> Dormitorylist = doritorydao.FindDormitoryAll(dormitory, page);
		// 判断这个宿舍内是否为空，如果为空，获取添加学生的性别，进行赋值
		if (Dormitorylist.get(0).getStudentdormitory().size() == 0) {
			Student student = studentdao.getById(studentDormitory.getStudent().getId());
			SexConst sex = student.getSex();
			dormitory.setSex(sex.code());
			doritorydao.DormitoryUpdate(dormitory);
		}
		studentdormitorydao.StudentDormitoryAdd(studentDormitory);
		// 更改学生住宿信息
		Student student = new Student();
		student.setId(studentDormitory.getStudent().getId());
		// 不能改1，因为当未住宿改为0===sql过不去
		student.setDormitorynote(2);
		studentdao.update(student);
	}

	// 宿舍人员退宿：不可更改
	@Override
	public void studentexit(StudentDormitory studentDormitory) {
		// TODO Auto-generated method stub
		Student student = new Student();
		// 更改学生表的住宿情况
		student.setId(studentDormitory.getStudent().getId());
		student.setDormitorynote(3);
		studentdao.update(student);
		// 修改学生住宿表的信息
		studentdormitorydao.UpdatestudentdormitoryBySidAndDid(studentDormitory);

	}

	// 宿舍人员移除成员
	@Override
	public void studentremove(StudentDormitory studentDormitory) {
		// TODO Auto-generated method stub
		Student student = new Student();
		/*
		 * 更改学生表的住宿情况 、 1未住宿 2在学校住宿 3不在学校住宿 并且出去住宿
		 * 
		 * 
		 */
		student.setId(studentDormitory.getStudent().getId());
		student.setDormitorynote(1);
		studentdao.update(student);
		// 修改学生住宿表的信息
		studentDormitory.setDormitoryleader(0);
		studentdormitorydao.UpdatestudentdormitoryBySidAndDid(studentDormitory);
		// 判断这个宿舍是否还有人居住，没有就把性别清空
		List<StudentDormitory> list = studentdormitorydao.FindStudentDormitoryBydid(studentDormitory.getDormitroy().getId());
		Dormitory dormitory = new Dormitory();
		dormitory.setId(studentDormitory.getDormitroy().getId());
		dormitory.setSex(0);
		if (list.size() == 0) {
			doritorydao.DormitoryUpdate(dormitory);
		}
	}

	// 设置为宿舍长
	@Override
	public void DormitorylleaderSystem(String studentid, Integer did) {
		// TODO Auto-generated method stub
		Integer sid = Integer.parseInt(studentid);
		// 获取这个宿舍的宿舍长信息，进行clean
		List<StudentDormitory> list = studentdormitorydao.FindStudentDormitoryBydid(did);
		for (StudentDormitory studentDormitory : list) {
			if (studentDormitory.getDormitoryleader() == 1) {
				Integer sdid = studentDormitory.getId();
				Dormitory dormitroy = studentDormitory.getDormitroy();
				StudentDormitory sd = new StudentDormitory();
				sd.setId(sdid);
				sd.setDormitoryleader(0);
				System.err.println("要修改宿舍长信息前" + sd);
				studentdormitorydao.DormitorylleaderSystemByid(sd);
			}
		}
		// 将设为宿舍长的学生ID赋值
		StudentDormitory sd = new StudentDormitory();
		// 学生的ID
		Student student = new Student();
		student.setId(sid);
		sd.setStudent(student);
		sd.setDormitoryleader(1);
		System.err.println("要修改宿舍长信息后" + sd);
		studentdormitorydao.DormitorylleaderSystemBysid(sd);
	}
	//通过学生id查看住宿情况
	@Override
	public StudentDormitory getBoarder(Integer id) {
		return studentdormitorydao.getBoarder(id);
	}

	

}
