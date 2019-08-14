package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.TeacherDao;
import cn.deepcoding.model.Teacher;
import cn.deepcoding.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Teacher getById(Integer id) {
		// TODO Auto-generated method stub
		return teacherDao.getById(id);
	}

	@Override
	public List<Teacher> findTeachers(Integer classroomId) {
		
		return teacherDao.findTeachers(classroomId);
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return teacherDao.getAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		teacherDao.delete(id);
	}

	@Override
	public void save(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherDao.save(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherDao.update(teacher);
	}

	@Override
	public List<Teacher> getState() {
		// TODO Auto-generated method stub
		return teacherDao.getState();
	}

	
	
	

}
