package cn.deepcoding.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import cn.deepcoding.model.StudentDormitoryQuerry;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.DormitoryService;

@Service
@Transactional
public class DormitoryServiceImpl implements DormitoryService {
	@Autowired
	private StudentDormitoryDao studentdormitorydao;
	@Autowired
	private DormitoryDao doritorydao;
	@Autowired
	private StudentDao studentdao;

	// 返回所有
	@Override
	public PageData FindDormitoryAll(Dormitory dormitory, String StudentName, Page page) {
		// TODO Auto-generated method stub
		System.err.println(page.getPage() + "////" + page.getRows() + "///" + page.getStart());

		List<Dormitory> list = doritorydao.FindDormitoryAll(dormitory, page);
		Integer count = doritorydao.getAllCount(dormitory);
		PageData pageData = new PageData();
		pageData.setTotal(count);

		// 判断宿舍长并在前台展示
		for (Dormitory dormitory2 : list) {
			List<StudentDormitory> studentdormitory = dormitory2.getStudentdormitory();
			for (StudentDormitory studentDormitory2 : studentdormitory) {
				Integer dormitoryleader = studentDormitory2.getDormitoryleader();
				String name = studentDormitory2.getStudent().getName();
				if (dormitoryleader == 1) {

					name = "*宿舍长*：" + name;
					studentDormitory2.getStudent().setName(name);
				}
			}
		}
		

		// 遍历宿舍学生，并把实际人数进行展示
		for (int i = 0; i < list.size(); i++) {

			Dormitory dorm = list.get(i);
			// 将实际的宿舍人员人数存储
			dorm.setActual(dorm.getStudentdormitory().size());

		}

		System.err.println("要查询的名字：" + StudentName);
		if (StudentName != null && StudentName != "") {
			// 姓名模糊查询，获取学生的ID
			Student student = new Student();
			student.setName(StudentName);
			List<Student> list2 = studentdao.getStudent(student);
			List<Dormitory> Dormitorynewlist = new ArrayList<Dormitory>();
			for (Student student2 : list2) {
				Integer id = student2.getId();
				for (Dormitory dormitor : list) {
					for (StudentDormitory studentDormitory2 : dormitor.getStudentdormitory()) {
						if (studentDormitory2.getStudent().getId() == id) {
							if (Dormitorynewlist.size() == 0) {
								Dormitorynewlist.add(dormitor);
							} else {
								boolean a = false;
								for (int i = 0; i < Dormitorynewlist.size(); i++) {
									if (Dormitorynewlist.get(i).getNumber() == dormitor.getNumber()) {
										a = true;
									}
								}
								if (a) {
								} else {
									Dormitorynewlist.add(dormitor);
								}
							}

						}
					}

				}
			}
			Collections.sort(Dormitorynewlist);
			pageData.setRows(Dormitorynewlist);
			return pageData;
		}
		Collections.sort(list);
		pageData.setRows(list);
		return pageData;
	}

	// 添加宿舍
	@Override
	public String DormitoryAdd(Dormitory dormitory) {
		// TODO Auto-generated method stub
		
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		page.setStart(0);
		
		List<Dormitory> list = doritorydao.FindDormitoryAll(dormitory, page);
		if (list.size() != 0) {
			return "error";
		}
		doritorydao.DormitoryAdd(dormitory);

		return "success";
	}

	// 过滤未住宿人员
	@Override
	public List<Student> getstudnt(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<Student> list2 = new ArrayList<Student>();
		// 获取要添加宿舍的ID性别
		String id = (String) request.getSession().getAttribute("did");
		Dormitory dormitory = new Dormitory();
		Integer did = Integer.parseInt(id);
		dormitory.setId(did);
		//自己手动赋值
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		page.setStart(0);
		List<Dormitory> Dormitorylist = doritorydao.FindDormitoryAll(dormitory, page);
		
		
		// 判断这个宿舍内是否为空，如果为空，不获取性别，直接获取所有学生
		if (Dormitorylist.get(0).getStudentdormitory().size() == 0) {
			List<Student> list = studentdao.getStudent(null);

			for (Student student : list) {

				if (student.getDormitorynote() == 1) {
					list2.add(student);
				}
			}
			return list2;

		}
		// 判断这个宿舍内是否为空，如果不为空，获取性别，判断性别获取学生
		Integer sex = Dormitorylist.get(0).getSex();

		List<Student> list = studentdao.getStudent(null);

		for (Student student : list) {
			int studentsex = student.getSex().code();
			if (student.getDormitorynote() == 1 && studentsex == sex) {
				list2.add(student);
			}
		}
		return list2;
	}

	// 通过ID查看宿舍成员信息，
	@Override
	public List<StudentDormitoryQuerry> GetStudentDormitoryById(Dormitory dormitory) {
		// TODO Auto-generated method stub
		
		//自己手动赋值
				Page page = new Page();
				page.setPage(1);
				page.setRows(10);
				page.setStart(0);
		List<Dormitory> list = doritorydao.FindDormitoryAll(dormitory, page);

		List<StudentDormitoryQuerry> list2 = new ArrayList<StudentDormitoryQuerry>();
		Integer id = null;
		String time = null;
		String name = null;
		String starttime = null;
		String adress = null;
		String note = null;
		for (Dormitory dormitory2 : list) {
			time = dormitory2.getTime();
			adress = dormitory2.getAdress();
			if ("".equals(dormitory2.getNote())) {
				note = "空";
			} else {
				note = dormitory2.getNote();
			}
			System.err.println(time);
			List<StudentDormitory> studentdormitory = dormitory2.getStudentdormitory();

			for (StudentDormitory studentDormitory2 : studentdormitory) {
				id = studentDormitory2.getStudent().getId();
				name = studentDormitory2.getStudent().getName();
				starttime = studentDormitory2.getStarttime();
				list2.add(new StudentDormitoryQuerry(id, name, starttime, time, adress, note));
			}

		}

		return list2;
	}

	// 通过ID查询宿舍信息
	@Override
	public Dormitory GetById(Dormitory dormitory) {
		// TODO Auto-generated method stub
		Dormitory dor = doritorydao.GetById(dormitory.getId());
		System.err.println(dor);
		if (dor.getNote() == "") {
			dor.setNote("空");
		}
		return dor;
	}

	// 修改宿舍信息
	@Override
	public String DormitoryUpdate(Dormitory dormitory) {
		// TODO Auto-generated method stub

		doritorydao.DormitoryUpdate(dormitory);

		return "success";

	}

	// 删除宿舍
	@Override
	public void deleteDormitory(Integer id) {
		// TODO Auto-generated method stub
		// 将宿舍隐藏不显示
		Dormitory dormitory = new Dormitory();
		dormitory.setId(id);
		dormitory.setHidden(1);
		doritorydao.DormitoryUpdate(dormitory);

		Dormitory dormito = doritorydao.GetById(id);

		List<StudentDormitory> studentdormitory = dormito.getStudentdormitory();
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String endtime = df.format(new Date());
		for (StudentDormitory studentDormitory2 : studentdormitory) {
			// 获取宿舍记录的ID
			Integer id2 = studentDormitory2.getId();
			// 获取宿舍记录的学生ID
			Integer sid = studentDormitory2.getStudent().getId();

			StudentDormitory studentDormitory3 = new StudentDormitory();

			studentDormitory3.setId(id2);
			studentDormitory3.setEndtime(endtime);
			// 将时间赋值到结束时间
			studentdormitorydao.UpdatestudentdormitorById(studentDormitory3);
			// 将学生住宿记录修改

			Student student = new Student();
			student.setId(sid);
			student.setDormitorynote(1);
			studentdao.update(student);
		}

	}

	// 获取所有的宿舍号接口
	@Override
	public List<Dormitory> listAllnumber() {
		// TODO Auto-generated method stub
		
		
		
		List<Dormitory> list = doritorydao.FindDormitorynumber();
		return list;
	}
	

}
