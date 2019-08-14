package cn.deepcoding.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.ProxyCommissionDao;
import cn.deepcoding.dao.ProxyCommissionDetailsDao;
import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.ProxyTeaMonth;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.ProxyTeacherHistory;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.ProxyTeacherPoint;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyCommissionDetailsService;
import cn.deepcoding.service.ProxyCommissionService;

@Service
@Transactional
public class ProxyCommissionServiceImpl implements ProxyCommissionService {

	@Autowired
	private ProxyCommissionDao proxyCommissionDao;
	@Autowired
	private StudentDao studentdao;
	@Autowired
	private ProxyTeacherDao proxyTeacherDao;

	@Autowired
	private ProxyCommissionDetailsDao proxyCommissionDetailsDao;

	@Autowired
	private ProxyCommissionDetailsService proxyCommissionDetailsService;

	// 查询当月缴纳学费总金额
	@Override
	public Double totalAmount(Date date) {
		String sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		return proxyCommissionDao.totalAmount(sd);
	}

	// 查询未缴纳学总金额
	@Override
	public Integer commissionno() {

		return proxyCommissionDao.commissionNo();
	}

	// 每个招生老师的提成
	@Override
	public HashMap<Integer, Double> everyStudent(Date date) {
		/* List<StudentBean> list = proxyCommissionDao.everyStudent(date); */
		HashMap<Integer, Double> map = new HashMap<>();
		List<ProxyTeacherHistory> lists = proxyCommissionDao.selectEveryProxyTeacherAmount(date);
		for (ProxyTeacherHistory proxyTeacherHistory : lists) {
			map.put(proxyTeacherHistory.getProxyteacherId(), proxyTeacherHistory.getCommissionAmount());
		}

		return map;
	}

	// 查找学生的直接招生老师ID
	public Integer stuAmount(Integer id) {
		return proxyCommissionDao.stuAmount(id);
	}

	// 查找老师点位
	@Override
	public String teacherPoint(Integer id) {
		return proxyCommissionDao.teacherPoint(id);
	}

	// 返回页面的值
	@Override
	public PageData findNoParent(Page page) {
		HashMap<Integer, Double> map = everyStudent(new Date());// 当月每个招生老师的提成
		HashMap<Integer, Double> mapNotPaid = proxyCommissionDetailsService.hasNotPaid();// 每个招生老师未获得的提成
		List<ProxyTeacherAmount> list = proxyCommissionDao.findNoParent(page);// 团队长名单
		for (ProxyTeacherAmount proxyTeacherAmount : list) {
			List<ProxyTeacher> puisneTeachers = new ArrayList<>();
			puisneTeachers = childProxyTeacher(puisneTeachers, proxyTeacherAmount.getId());// 通过招生老师
																							// id
																							// 拿到他名下的所有招生老师的信息集合
			if (puisneTeachers != null) {
				List<Integer> list1 = new ArrayList<>();
				for (ProxyTeacher pt : puisneTeachers) {// 遍历下级集合 将下级招生老师的 id 放入
														// list1 集合中
					list1.add(pt.getId());
				}
				Double sum = 0.00;// 可提成总额
				Double sumNotPaid = 0.00;// 未提成总额
				for (Integer i : list1) {
					if (map.get(i) != null && map.containsKey(i)) {
						sum += map.get(i);// 每个团队的总数
					}
					if (mapNotPaid.get(i) != null && mapNotPaid.containsKey(i)) {
						sumNotPaid += mapNotPaid.get(i);
					}
				}
				if (map.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setAmount(sum);
				} else {
					proxyTeacherAmount.setAmount(Math.floor(map.get(proxyTeacherAmount.getId()) + sum));
				}
				if (mapNotPaid.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setNoPaid(sumNotPaid);
				} else {
					proxyTeacherAmount.setNoPaid(Math.floor(mapNotPaid.get(proxyTeacherAmount.getId()) + sumNotPaid));
				}

			} else {
				if (map.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setAmount(0.00);
				} else {
					proxyTeacherAmount.setAmount(Math.floor(map.get(proxyTeacherAmount.getId())));
				}
				if (mapNotPaid.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setNoPaid(0.00);
				} else {
					proxyTeacherAmount.setNoPaid(Math.floor(mapNotPaid.get(proxyTeacherAmount.getId())));
				}
			}

		}
		int rows = proxyCommissionDao.getTotal();
		PageData pageData = new PageData();
		pageData.setRows(list);
		pageData.setTotal(rows);
		return pageData;
	}

	public List<ProxyTeacher> childProxyTeacher(List<ProxyTeacher> puisneTeachers, Integer ptid) {
		List<ProxyTeacher> list = new ArrayList<>();
		if (ptid != null) {
			list = proxyCommissionDetailsDao.getParentProxyTeacherId(ptid);
			if (list != null && list.size() > 0) {
				for (ProxyTeacher teacher : list) {
					puisneTeachers.add(teacher);
					childProxyTeacher(puisneTeachers, teacher.getId());
				}
			} else {
				return null;
			}
		}
		return puisneTeachers;
	}

	// 保存学费并换算点位，提成到历史表里
	@Override
	public void save(Fee fee) {
		Integer studentId = fee.getStudent().getId();// 学生id
		Integer proxyTeacherId = proxyCommissionDao.stuAmount(studentId);// 直招老师id
		ProxyTeacherHistory proxyTeacherHistory = new ProxyTeacherHistory();
		ProxyTeacher proxyTeacher = new ProxyTeacher();
		Student student =new Student();
		Integer leaderId = null;
		String joinTime = null;
		Double point1 = null;
		if (proxyTeacherId != null) {

			proxyTeacher = proxyCommissionDao.findById(proxyTeacherId);// 直招老师

			proxyTeacherHistory.setProxyteacherId(proxyTeacherId);
			proxyTeacherHistory.setStudentId(studentId);
			proxyTeacherHistory.setProxyteacherName(proxyTeacher.getName());
			proxyTeacherHistory.setStudentName(fee.getStudent().getName());
			proxyTeacherHistory.setStudentTuitionAmount(fee.getAmount());

			/**
			 * 2019-0-20修改 点位从点位表拿到数据
			 * 
			 * 
			 */
			/**
			 * 根据学生ID查看入学时间
			 * 
			 */
			 student = studentdao.getById(studentId);
			// 获取学生入学时间
			joinTime = student.getJoinTime();
			proxyTeacher.setId(proxyTeacherId);
			student.setProxyTeacher(proxyTeacher);
			//通过招生老师ID与学生入学时间获取当月招生老师的点位
			point1 = proxyTeacherDao.getpointByStudentJointimeAndProxyTeacherId(student);
			System.err.println("该入校时间直招老师的点位是：" + point1);
			proxyTeacherHistory.setCommissionPoint(point1);// 提成点位
			proxyTeacherHistory.setPoint(point1);// 当前点位
			proxyTeacherHistory.setCommissionAmount(fee.getAmount() * point1 / 100);// 提成金额
			proxyTeacherHistory.setCommissionDate(fee.getPaymentTime());// 提成时间
			proxyCommissionDao.saveHistory(proxyTeacherHistory);
			leaderId = proxyCommissionDao.findParentTeacherId(proxyTeacherId);
		}

		while (leaderId != null) {
			if (leaderId == 0) {
				break;
			}
			proxyTeacher = proxyCommissionDao.findById(leaderId);// 上级老师
			proxyTeacherHistory.setProxyteacherId(leaderId);
			proxyTeacherHistory.setStudentId(studentId);
			proxyTeacherHistory.setProxyteacherName(proxyTeacher.getName());
			proxyTeacherHistory.setStudentName(fee.getStudent().getName());
			proxyTeacherHistory.setStudentTuitionAmount(fee.getAmount());
			//通过招生老师ID与学生入学时间获取当月招生老师的点位
			proxyTeacher.setId(leaderId);
			student.setProxyTeacher(proxyTeacher);
			Double	point3 = proxyTeacherDao.getpointByStudentJointimeAndProxyTeacherId(student);
			System.err.println("该入校时间上上级老师的点位是：" + point3);
			proxyTeacherHistory.setPoint(point1);// 上级招生老师当前点位
			Double diffPoint = point3 - point1;// 点位差
			proxyTeacherHistory.setCommissionPoint(diffPoint);
			proxyTeacherHistory.setCommissionAmount(fee.getAmount() * diffPoint / 100);
			proxyTeacherHistory.setCommissionDate(fee.getPaymentTime());
			proxyCommissionDao.saveHistory(proxyTeacherHistory);
			proxyTeacherId = leaderId;
			leaderId = proxyCommissionDao.findParentTeacherId(leaderId);
		}
	}

	// excel导出
	public List<ProxyTeacherAmount> export(Page page) {
		HashMap<Integer, Double> map = everyStudent(new Date());// 当月每个招生老师的提成
		HashMap<Integer, Double> mapNotPaid = proxyCommissionDetailsService.hasNotPaid();// 每个招生老师未获得的提成
		List<ProxyTeacherAmount> list = proxyCommissionDao.findNoParent(page);// 团队长名单
		for (ProxyTeacherAmount proxyTeacherAmount : list) {
			List<ProxyTeacher> puisneTeachers = new ArrayList<>();
			puisneTeachers = childProxyTeacher(puisneTeachers, proxyTeacherAmount.getId());// 通过招生老师
																							// id
																							// 拿到他名下的所有招生老师的信息集合
			if (puisneTeachers != null) {
				List<Integer> list1 = new ArrayList<>();
				for (ProxyTeacher pt : puisneTeachers) {// 遍历下级集合 将下级招生老师的 id 放入
														// list1 集合中
					list1.add(pt.getId());
				}
				Double sum = 0.00;// 可提成总额
				Double sumNotPaid = 0.00;// 未提成总额
				for (Integer i : list1) {
					if (map.get(i) != null && map.containsKey(i)) {
						sum += map.get(i);// 每个团队的总数
					}
					if (mapNotPaid.get(i) != null && mapNotPaid.containsKey(i)) {
						sumNotPaid += mapNotPaid.get(i);
					}
				}
				if (map.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setAmount(sum);
				} else {
					proxyTeacherAmount.setAmount(Math.floor(map.get(proxyTeacherAmount.getId()) + sum));
				}
				if (mapNotPaid.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setNoPaid(sumNotPaid);
				} else {
					proxyTeacherAmount.setNoPaid(Math.floor(mapNotPaid.get(proxyTeacherAmount.getId()) + sumNotPaid));
				}

			} else {
				if (map.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setAmount(0.00);
				} else {
					proxyTeacherAmount.setAmount(Math.floor(map.get(proxyTeacherAmount.getId())));
				}
				if (mapNotPaid.get(proxyTeacherAmount.getId()) == null) {
					proxyTeacherAmount.setNoPaid(0.00);
				} else {
					proxyTeacherAmount.setNoPaid(Math.floor(mapNotPaid.get(proxyTeacherAmount.getId())));
				}
			}

		}
		return list;
	}

	@Override
	public PageData history(String name, Date starttime, Date endtime, Page page) {
		List<ProxyTeaMonth> lists = proxyCommissionDao.history(name, starttime, endtime, page);
		Integer rows = proxyCommissionDao.historyTotal(name, starttime, endtime);
		PageData pd = new PageData();
		pd.setRows(lists);
		pd.setTotal(rows);

		return pd;
	}

}
