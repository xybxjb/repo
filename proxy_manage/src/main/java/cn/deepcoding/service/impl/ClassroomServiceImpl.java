package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ClassroomDao;
import cn.deepcoding.dao.TeacherDao;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ClassroomService;
@Service
@Transactional
public class ClassroomServiceImpl implements ClassroomService{

	@Autowired
	private ClassroomDao classroomDao;
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public Classroom getById(Integer id) {
		// TODO Auto-generated method stub
		return classroomDao.getById(id);
	}
	//获取所有的班级，包括有结束时间的班级
	@Override
	public List<Classroom> getAll() {
		
		return classroomDao.getAll();
	}
	//获取所有状态为存在的班级
	@Override
	public List<Classroom> getAllBe() {
		
		return classroomDao.getAllBe();
	}
	@Override
	public void delete(Integer id) {
		classroomDao.delete(id);
	}
	@Override
	public void update(Classroom classroom) {
		classroomDao.update(classroom);
	}
	@Override
	public void save(Classroom classroom,Integer [] teacherId) {
		classroomDao.saveClassroom(classroom);
		for(int teacherId2 :teacherId){
			classroomDao.saveTeacherClassroom(classroom, teacherId2);
		}
		//classroomDao.save(classroom, teacherId);
	}
	
	@Override
	public void updateTeacherByClassId(Classroom classroom, Integer[] teacherIds) {
		classroomDao.updateTeacherByClassId(classroom, teacherIds);
		List<Teacher> teachers = teacherDao.getTeacherByClassroom(classroom.getId());
		List<Integer> teaIds =new ArrayList<>();
		/*for(Teacher  teacher:teachers ){
			teaIds.add(teacher.getId());
		}*/
		for(int i = 0;i<teachers.size();i++){
			teaIds.add(teachers.get(i).getId());
		}
		for(int i=0; i<teacherIds.length;i++){
			if(!teaIds.contains(teacherIds[i])){
				classroomDao.insertTeacherByClassId(classroom, teacherIds[i]);
			}
		}
	
		
		
		
	}
	
	
}
