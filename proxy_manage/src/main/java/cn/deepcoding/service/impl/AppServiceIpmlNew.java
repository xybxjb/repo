package cn.deepcoding.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.json.JSONObject;
import org.noggit.JSONUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.deepcoding.dao.AppUsersDao;
import cn.deepcoding.dao.AttenceDao;
import cn.deepcoding.dao.ExamDao;
import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.GraduatedDao;
import cn.deepcoding.dao.GraduatedSearch;
import cn.deepcoding.dao.GradutionPresonDao;
import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.ReferrerDao;
import cn.deepcoding.dao.ScoreDao;
import cn.deepcoding.dao.StudentConversationDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.dao.StudentProxyTeacherDao;
import cn.deepcoding.model.AppUsers;
import cn.deepcoding.model.Attence;
import cn.deepcoding.model.AttenceCount;
import cn.deepcoding.model.AttenceGroup;
import cn.deepcoding.model.Code;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.FindJodPreson;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.GraduatedResult;
import cn.deepcoding.model.PresonAddress;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherWatch;
import cn.deepcoding.model.QueryVo2;
import cn.deepcoding.model.Referrer;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.redis.RedisService;
import cn.deepcoding.service.AppServiceNew;
import cn.deepcoding.util.ConstUtils;
import cn.deepcoding.util.MessageXsendDemo;
import cn.deepcoding.util.ServerResponse;

@Service
public class AppServiceIpmlNew implements AppServiceNew {

	@Autowired
	private ProxyTeacherDao proxyTeacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private FeeDao feeDao;
	@Autowired
	private GraduatedDao graduatedDao;
	@Autowired
	private GraduatedSearch graduatedSearch;
	@Autowired
	private AttenceDao attenceDao;
	@Autowired
	private ExamDao examDao;
	@Autowired
	private ScoreDao scoreDao;
	@Autowired
	private StudentConversationDao studentConversationDao;
	@Autowired
	private AppUsersDao appUsersDao;
	@Autowired
	private RedisService redisService;
	@Autowired 
	private StudentProxyTeacherDao studentProxyTeacherDao;
	@Autowired
	private ReferrerDao referrerDao;
	@Autowired
	private GradutionPresonDao gradutionPresonDao;
	// 根据 招生老师 id 查询自己信息
	@Override
	public ServerResponse getProxyTeacher(String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		String id = redisService.hget("APPLOGIN", token);
		ProxyTeacher parentProxyTeacher = proxyTeacherDao.getParentProxyTeacher(Integer.parseInt(id));
		parentProxyTeacher.setHeadPortrait("/images/bankPic/7e639a14-9e53-4fc8-bc7f-6d05af317fbd.jpg");
		AppUsers appusers = appUsersDao.getByTel(parentProxyTeacher.getTel());
		System.out.println("---------appusers---------"+appusers);
		parentProxyTeacher.setLeaderName(appusers.getAppName());
		return ServerResponse.getSuccess("SUCCESS", parentProxyTeacher);
	}

	// 根据 招生老师 id 查询上级信息
	@Override
	public ServerResponse getParentProxyTeacher(String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		Integer id =Integer.parseInt(redisService.hget("APPLOGIN", token));
		Integer parentProxyTeacherId2 = proxyTeacherDao.getParentProxyTeacherId2(id);
		if (parentProxyTeacherId2 != null) {
			ProxyTeacher parentProxyTeacher2 = proxyTeacherDao.getParentProxyTeacher(parentProxyTeacherId2);
			Integer id2 = parentProxyTeacher2.getId();
			// System.err.println(name);
			int studentCount = studentDao.getStudentCountByProxyTeacherId(id2);
			parentProxyTeacher2.setStudentCount(studentCount);
			return ServerResponse.getSuccess("SUCCESS", parentProxyTeacher2);
		} else {
			return ServerResponse.getSuccess("无上级老师信息");
		}

	}

	// 根据招生老师 id 查询下级信息
	@Override
	public ServerResponse getSublevelProxyTeacher(String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		Integer id =Integer.parseInt(redisService.hget("APPLOGIN", token));
		List<ProxyTeacher> sublevelProxyTeacher = proxyTeacherDao.getSublevelProxyTeacher(id);
		if (sublevelProxyTeacher != null) {
			for (ProxyTeacher proxyTeacher : sublevelProxyTeacher) {
				Integer id2 = proxyTeacher.getId();
				int studentCount = studentDao.getStudentCountByProxyTeacherId(id2);
				proxyTeacher.setStudentCount(studentCount);
				System.out.println(proxyTeacher);
			}
			return ServerResponse.getSuccess("SUCCESS", sublevelProxyTeacher);
		} 
		return ServerResponse.getError("无下级老师信息");
		
	}

	// 根据招生老师 id 查询学生信息
	@Override
	public ServerResponse getStudentByProxyTeacherId2(Integer id1,String token, Page page) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		if(id1 != null){
			List<Student> studentByProxyTeacherId1 = studentDao.getStudentByProxyTeacherId2(id1, page);
			int studentCountByProxyTeacherId1 = studentDao.getStudentCountByProxyTeacherId(id1);
			if(studentByProxyTeacherId1 != null){
				for (Student student : studentByProxyTeacherId1) {
					// 获取已交费用
					Integer feeSelect = feeDao.feeSelect(student.getId());
					if (feeSelect != null) {
						student.setAmountTuition(feeSelect);
					} else {
						student.setAmountTuition(0.0);
					}
				}
				PageData pageDate = new PageData();
				pageDate.setRows(studentByProxyTeacherId1);
				pageDate.setTotal(studentCountByProxyTeacherId1);
				return ServerResponse.getSuccess("SUCCESS", pageDate);
				
			}else{
				return ServerResponse.getError("ERROR");
			}
		}
		Integer id2 =Integer.parseInt(redisService.hget("APPLOGIN", token));
		// 获取学生信息
		List<Student> studentByProxyTeacherId2 = studentDao.getStudentByProxyTeacherId2(id2, page);		
		// 学生信息个数
		int studentCountByProxyTeacherId2 = studentDao.getStudentCountByProxyTeacherId(id2);		

		if (studentByProxyTeacherId2 != null) {
			for (Student student : studentByProxyTeacherId2) {
				// 获取已交费用
				Integer feeSelect = feeDao.feeSelect(student.getId());
				if (feeSelect != null) {
					student.setAmountTuition(feeSelect);
				} else {
					student.setAmountTuition(0.0);
				}
			}
			PageData pageDate = new PageData();
			pageDate.setRows(studentByProxyTeacherId2);
			pageDate.setTotal(studentCountByProxyTeacherId2);
			return ServerResponse.getSuccess("SUCCESS", pageDate);
		} else{
			return ServerResponse.getError("ERROR");
		}				
	}

	// 根据学生 id 查询学生信息
	@Override
	public ServerResponse getStudentById3(Integer id,String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		Student studentById3 = studentDao.getStudentById3(id);
		if (studentById3 == null) {
			return null;
		}
		// 获取已交费用
		Integer feeSelect = feeDao.feeSelect(studentById3.getId());
		if (feeSelect != null) {
			studentById3.setAmountTuition(feeSelect);
		} else {
			studentById3.setAmountTuition(0.0);

		}
		return ServerResponse.getSuccess("SUCCESS", studentById3);
	}

	// 根据学生 id 查询学生缴费信息
	@Override
	public ServerResponse getFeeByStudentId2(Integer id ,String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		List<Fee> feeByStudentId2 = feeDao.getFeeByStudentId2(id);
		if(feeByStudentId2 != null){
			PageInfo<Fee> inif = new PageInfo<Fee>(feeByStudentId2);
			Map<String,Object> data = new HashMap<>();
			data.put("total", inif.getTotal());
			data.put("rows", feeByStudentId2);
			return ServerResponse.getSuccess("SUCCESS", data);
		}else{
			return ServerResponse.getError("无此学生缴费信息!");
		}
		
	}

	// 根据学生 id 查询 钉钉唯一标识符并获取考勤信息
	@Override
	public ServerResponse getAttenceByStudentId1(Integer id,String token) throws Exception {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		List<QueryVo2> lqy = new ArrayList<QueryVo2>();
		QueryVo2 qy1 = new QueryVo2();
		QueryVo2 qy2 = new QueryVo2();
		QueryVo2 qy3 = new QueryVo2();

		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);
		// 入学时间
		String joinTime = attenceByStudentId.getJoinTime();
		// 该学生的 钉钉唯一标识符
		String dingId = attenceByStudentId.getDingId();
		// 学生姓名
		String name = attenceByStudentId.getName();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		// System.out.println(joinTime);
		Date d1 = df.parse(joinTime);
		long daysBetween = (date.getTime() - d1.getTime()) / (60 * 60 * 24 * 1000); // 入学时间与现在时间的间隔
		if (daysBetween < 90) {
			// 一个月的考勤信息
			List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);

			AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
			attenceCount.setStudentName(name);
			// attenceCounts1.add(attenceCount);
			qy1.setStringWork("最近一个月");
			qy1.setAttenceCount(attenceCount);
			lqy.add(qy1);
		} else if (daysBetween > 90 & daysBetween < 180) {
			// 一个月的考勤信息
			List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);

			AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
			attenceCount.setStudentName(name);
			// attenceCounts1.add(attenceCount);
			qy1.setStringWork("最近一个月");
			qy1.setAttenceCount(attenceCount);
			lqy.add(qy1);
			// 三个月的考勤信息
			List<AttenceGroup> attenceCount2 = attenceDao.getAttenceCount2(dingId);

			AttenceCount attenceCount3 = getAttenceByStudentId2(attenceCount2);
			attenceCount3.setStudentName(name);
			// attenceCounts2.add(attenceCount3);
			qy2.setStringWork("最近三个月");
			qy2.setAttenceCount(attenceCount3);
			lqy.add(qy2);
		} else if (daysBetween > 180) {
			// 一个月的考勤信息
			List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);
			// AttenceCount attenceCount = new AttenceCount();
			// for (AttenceGroup attenceGroup : attenceCount1) {
			// if(attenceGroup.getTimeResult() == 0){
			// attenceCount.setNormal(attenceGroup.getAmount());
			// }else if(attenceGroup.getTimeResult() == 1){
			// attenceCount.setEarly(attenceGroup.getAmount());
			// }else if(attenceGroup.getTimeResult()==2){
			// attenceCount.setLate(attenceGroup.getAmount());
			// }else if(attenceGroup.getTimeResult()==3){
			// attenceCount.setSeriousLate(attenceGroup.getAmount());
			// }else if(attenceGroup.getTimeResult()==4){
			// attenceCount.setAbsenteeism(attenceGroup.getAmount());
			// }else if(attenceGroup.getTimeResult()==5){
			// attenceCount.setNotSigned(attenceGroup.getAmount());
			// }
			// }
			AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
			attenceCount.setStudentName(name);
			// attenceCounts1.add(attenceCount);
			qy1.setStringWork("最近一个月");
			qy1.setAttenceCount(attenceCount);
			lqy.add(qy1);
			// 三个月的考勤信息
			List<AttenceGroup> attenceCount2 = attenceDao.getAttenceCount2(dingId);

			AttenceCount attenceCount3 = getAttenceByStudentId2(attenceCount2);
			attenceCount3.setStudentName(name);
			// attenceCounts2.add(attenceCount3);
			qy2.setStringWork("最近三个月");
			qy2.setAttenceCount(attenceCount3);
			lqy.add(qy2);
			// 六个月的考勤信息
			List<AttenceGroup> attenceCount4 = attenceDao.getAttenceCount2(dingId);

			AttenceCount attenceCount5 = getAttenceByStudentId2(attenceCount4);
			attenceCount5.setStudentName(name);
			// attenceCounts3.add(attenceCount5);
			// mla.put("最近六个月", attenceCounts3);
			qy3.setStringWork("最近六个月");
			qy3.setAttenceCount(attenceCount5);
			lqy.add(qy3);

		}
		return ServerResponse.getSuccess("SUCCESS", lqy) ;
	}

	// 考勤信息 公共类
	public AttenceCount getAttenceByStudentId2(List<AttenceGroup> attenceGroups) {
		AttenceCount attenceCount = new AttenceCount();
		for (AttenceGroup attenceGroup : attenceGroups) {
			if (attenceGroup.getTimeResult() == 0) {
				attenceCount.setNormal(attenceGroup.getAmount());
			} else if (attenceGroup.getTimeResult() == 1) {
				attenceCount.setEarly(attenceGroup.getAmount());
			} else if (attenceGroup.getTimeResult() == 2) {
				attenceCount.setLate(attenceGroup.getAmount());
			} else if (attenceGroup.getTimeResult() == 3) {
				attenceCount.setSeriousLate(attenceGroup.getAmount());
			} else if (attenceGroup.getTimeResult() == 4) {
				attenceCount.setAbsenteeism(attenceGroup.getAmount());
			} else if (attenceGroup.getTimeResult() == 5) {
				attenceCount.setNotSigned(attenceGroup.getAmount());
			}
		}
		return attenceCount;
	}

	// 根据学生查询 最近一个月的考勤明细
	@Override
	public ServerResponse getAttenceByStudent1(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		// 根据学生 id 查询学生部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);
		// 该学生的 钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();
		// 根据 钉钉唯一标识符 查询最近一个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate1(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);
		return ServerResponse.getSuccess("SUCCESS", etAttenceByStudent);
	}

	// 根据学生查询 最近三个月的考勤明细
	public ServerResponse getAttenceByStudent2(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		// 根据学生 id 查询部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);

		// 获取学生 的钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();
		// 根据 钉钉唯一标识符 查询最近三个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate2(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);
		return ServerResponse.getSuccess("SUCCESS", etAttenceByStudent);
	}

	// 查询学生最近六个月的明细表
	public ServerResponse getAttenceByStudent3(Integer id, String token) {
		// 根据学生 id 查询部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);
		// 获取学生 的钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();
		// 根据 钉钉唯一标识符 查询最近三个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate3(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);
		return ServerResponse.getSuccess("SUCCESS", etAttenceByStudent);
	}

	// 明细表 公共类
	public List<AttenceCount> etAttenceByStudent(List<Attence> attenceCount, Student student) {
		// 创建 attencount 集合
		List<AttenceCount> attenceCounts = new ArrayList<AttenceCount>();
		// 获取学生
		String name = student.getName();
		// 获取学生 的钉钉唯一标识符
		String userId = student.getDingId();
		attenceCount.forEach(workDates -> {
			// 根据 钉钉唯一标识符 和 日期 查询考勤信息
			List<AttenceGroup> attenceCountByWorkDate = attenceDao.getAttenceCountByWorkDate(userId,
					workDates.getWorkDate());
			// 创建 AttenceCount 对象
			AttenceCount attenceCount2 = new AttenceCount();
			attenceCountByWorkDate.forEach(attenceCountByWorkDates -> {
				if (attenceCountByWorkDates.getTimeResult() == 0) {
					attenceCount2.setNormal(attenceCountByWorkDates.getAmount());
				} else if (attenceCountByWorkDates.getTimeResult() == 1) {
					attenceCount2.setEarly(attenceCountByWorkDates.getAmount());
				} else if (attenceCountByWorkDates.getTimeResult() == 2) {
					attenceCount2.setLate(attenceCountByWorkDates.getAmount());
				} else if (attenceCountByWorkDates.getTimeResult() == 3) {
					attenceCount2.setSeriousLate(attenceCountByWorkDates.getAmount());
				} else if (attenceCountByWorkDates.getTimeResult() == 4) {
					attenceCount2.setAbsenteeism(attenceCountByWorkDates.getAmount());
				} else if (attenceCountByWorkDates.getTimeResult() == 5) {
					attenceCount2.setNotSigned(attenceCountByWorkDates.getAmount());
				}
			});
			attenceCount2.setStudentName(name);
			attenceCount2.setWorkDate(workDates.getWorkDate());
			attenceCounts.add(attenceCount2);
		});
		return attenceCounts;
	}

	// 根据 学生id 查询考试记录
	@Override
	public ServerResponse getExamByStudentId(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		List<Exam> examByStudentId = examDao.getExamByStudentId(id);
		if (examByStudentId != null) {
			for (Exam exam : examByStudentId) {
				Integer id2 = exam.getId();
				Integer scoreByExamId = scoreDao.getScoreByExamId(id2);
				exam.setScore(scoreByExamId);
				System.out.println(exam);
			}
			PageInfo<Exam> inif = new PageInfo<Exam>(examByStudentId);
			Map<String, Object> data = new HashMap<>();
			data.put("total", inif.getTotal());
			data.put("rows", examByStudentId);
			return ServerResponse.getSuccess("success", data);
		}
		return ServerResponse.getError("该生无考试记录!");
	}

	// 根据学生id查询学生谈话记录
	@Override
	public ServerResponse getStudentConversationById(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		List<StudentConversation> studentConversation = studentConversationDao.getById2(id);
		if (studentConversation != null) {
			PageInfo<StudentConversation> inif = new PageInfo<StudentConversation>(studentConversation);
			Map<String, Object> data = new HashMap<>();
			data.put("total", inif.getTotal());
			data.put("rows", studentConversation);
			return ServerResponse.getSuccess("SUCCESS", data);
		}
		return ServerResponse.getError("该生无谈话记录!");
	}

	// 根据id 查询 谈话记录详细表（图片）
	@Override
	public ServerResponse getStudentConversationPicById(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		String pic = studentConversationDao.getPicById(id);
		// 图片路径
		String url = ConstUtils.getString("image_url") + pic;
		HashMap<String, Object> map = new HashMap<>();
		map.put("url", url);
		return ServerResponse.getSuccess("SUCCESS", map);
	}

	// 根据学生 id 查询 钉钉唯一标识符
	@Override
	public List<Attence> getAttenceByStudentId(Integer id) {
		studentDao.getAttenceByStudentId(id);

		return null;

	}

	// 点击修改 点击量
	@Override
	public void updateStudentpageView(Integer id) {
		graduatedDao.updateStudentpageView(id);
	}

	// 每晚 两点定时更新 点击量
	@Override
	public void updateStudentpageView2(Graduated graduated) {
		Page page = new Page();
		page.setPage(1);
		page.setRows(1000);
		List<Graduated> listAll = graduatedDao.listAll(graduated, page);
		listAll.forEach(graduateds -> {
			Integer id = graduateds.getId();
			// 一个随机数
			Integer getrandom = getrandom();
			graduatedDao.updateStudentpageView2(getrandom, id);

		});

	}

	// 随机生成 一个 15-20的数
	public Integer getrandom() {
		int i = (int) (10 + Math.random() * (20 - 10 + 1));
		return i;
	}

	// 查询就业精英信息
	@Override
	public ServerResponse getElite() {
		List<Graduated> elite = graduatedDao.getElite();
		return ServerResponse.getSuccess("SUCCESS", elite);
	}

	// 根据 账号 查询 信息
	@Override
	public ServerResponse getByName(String appTel, String appPassword) {
		// 判断是否为空
		if (appTel == null || appPassword == null) {

			return ServerResponse.getError("请输入用户名和密码");
		}
		ProxyTeacher proxyTeacher = proxyTeacherDao.getProxyTeacherByTel2(appTel);
		// 判断手机号是否存在
		if (proxyTeacher == null) {
			return ServerResponse.getError("该用户不存在");
		}
		// 判断用户表内是否有这个用户信息
		AppUsers appusers = appUsersDao.getByTel(appTel);
		if (appusers != null) {
			if (appusers.getAppTel().equals(appTel) & appusers.getAppPassword().equals(appPassword)) {
				return getToken(proxyTeacher, appTel, appusers);
			}
			return ServerResponse.getError("密码错误");
		}
		if(!appPassword.equals("123456")){
			return ServerResponse.getError("初始密码错误");
		}
		// 当没有这个用户时 手动创建此用户
		AppUsers appusersNew = new AppUsers();
		String appName = getAppName();
		appusersNew.setAppName(appName);
		appusersNew.setAppPassword("123456");
		appusersNew.setAppTel(proxyTeacher.getTel());
		appusersNew.setProxyTeacherId(proxyTeacher.getId());
		appUsersDao.addAppUser(appusersNew);
		return getToken(proxyTeacher, appTel, appusersNew);

	}

	// 登陆成功，调用此返回方法
	public ServerResponse getToken(ProxyTeacher proxyTeacher, String appTel, AppUsers appusers) {
		// 首先删除上次用户信息,防止用户重复登陆
		String tokenOld = redisService.hget("APPLOGIN", appTel);
		if (tokenOld != null) {
			redisService.hdel("APPLOGIN", tokenOld);
		}
		// 生成token
		String token = UUID.randomUUID().toString().replace("-", "");
		// 把用户信息写入redis
		// 获取 招生老师 id
		String id = proxyTeacher.getId().toString();
		redisService.hset("APPLOGIN", appTel, token);
		redisService.hset("APPLOGIN", token, id);
		/// 设置时间为 3600 秒*24小时*7天
		redisService.expire(appTel, 3600 * 24 * 7);
		redisService.expire(token, 3600 * 24 * 7);
		// 客户端的tooken
		Map<String, Object> Map = new HashMap<>();
		Map.put("token", token);
		return ServerResponse.getSuccess("SUCCESS", Map);
	}

	// 发送短信
	@Override
	public ServerResponse getMessage(String tel) {
		AppUsers byTel = appUsersDao.getByTel(tel);
		if (byTel != null) {
			AppUsers appUser = new AppUsers();
			// 生成 六位 验证码
			String getrandom2 = getrandom2();
			System.err.println("手机号：" + tel + "验证码：" + getrandom2);
			// 将验证码与短信调用发送短信接口
			
			MessageXsendDemo.getmessige(tel, Integer.parseInt(getrandom2));
			// 保存到 redis
			redisService.set(tel, getrandom2);
			redisService.expire(tel, 300);
			return ServerResponse.getSuccess("发送成功！");
		}
		return ServerResponse.getError("手机号码不存在，请检查！");
	}
	/*
	 * 新发送短信功能
	 */
	@Override
	public ServerResponse sendMassage(String tel) {
		ProxyTeacher proxyTeacher = proxyTeacherDao.getProxyTeacherByTel2(tel);
		if(proxyTeacher != null) {
			// 生成 六位 验证码
			String getrandom2 = getrandom2();
			//System.err.println("手机号：" + tel + "验证码：" + getrandom2);
			// 将验证码与短信调用发送短信接口
			
			MessageXsendDemo.getmessige(tel, Integer.parseInt(getrandom2));
			// 保存到 redis
			redisService.set(tel, getrandom2);
			redisService.expire(tel, 300);
			return ServerResponse.getSuccess("发送成功！");
		}
		return ServerResponse.getError("手机号码不存在，请检查！");
	}


	// 修改密码
	@Override
	public ServerResponse updatePassword(String code, String appTel, String password) {
		// redis 获取验证码
		String rediscode = redisService.get(appTel);
		System.err.println(rediscode);
		if (null == rediscode) {
			return ServerResponse.getError("验证码失效，请重新获取!");
		}
		// 页面传过来的验证码
		if (rediscode.equals(code)) {
			AppUsers appUsers = new AppUsers();
			appUsers.setAppTel(appTel);
			appUsers.setAppPassword(password);
			appUsersDao.updatePassword(appUsers);
			redisService.del(appTel);
			return ServerResponse.getSuccess("修改成功");
		}
		return ServerResponse.getError("验证码不正确!");
	}

	// 生成六位随机数
	public String getrandom2() {
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

	// 随机生成一个九位appName
	public static String getAppName() {
		// 定义一个字符串（A-Z，a-z，0-9）即62位；
		String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		// 由Random生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 长度为几就循环几次
		for (int i = 0; i < 6; ++i) {
			// 产生0-61的数字
			int number = random.nextInt(62);
			// 将产生的数字通过length次承载到sb中
			sb.append(str.charAt(number));
		}
		// 将承载的字符转换成字符串
		return sb.toString();
		// System.out.println(sb.toString());
	}

	public static void main(String[] args) throws ParseException {
		/*
		 * Date date = new Date(); SimpleDateFormat df = new
		 * SimpleDateFormat("yyyy-MM-dd");// 设置日期格式 String date1 = "2018-12-26";
		 * String format = df.format(date); Date d1 = df.parse(format); Date d2
		 * = df.parse(date1); long daysBetween = (d1.getTime() - d2.getTime()) /
		 * (60 * 60 * 24 * 1000); System.out.println(format);
		 * System.out.println(d1); System.out.println(daysBetween); // 图片路径
		 * String string = ConstUtils.getString("image_url");
		 * System.out.println(string); int i = (int) (15 + Math.random() * (20 -
		 * 15 + 1)); System.out.println(i);
		 */

	}

	// 根据地址和用户名查询就业信息
	@Override
	public GraduatedResult getGraduatedByaddress(String keyword, int page, int rows) {
		// 创建一个solrquery对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(keyword);
		// 设置分页条件
		if (page <= 0) {
			page = 1;
		}
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "graduated_title");
		// 开启高亮显示
		query.setHighlight(true);
		// 设置高亮显示的域
		query.addHighlightField("graduated_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 调用dao执行查询
		GraduatedResult graduatedResult = null;
		try {
			graduatedResult = graduatedSearch.srarch(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 计算总页数
		long recordCount = graduatedResult.getRecordCount();
		int totalpage = (int) (recordCount / rows);
		if (totalpage >= 0) {
			totalpage++;
		}
		// 将总页数添加到返回结果
		graduatedResult.setTotalPages(totalpage);
		// 返回结果
		return graduatedResult;
	}

	// 根据id查询就业信息
	@Override
	public ServerResponse getGraduatedById(Integer id) {
		Graduated graduated = graduatedDao.getGraduatedById(id);
		graduatedDao.updateStudentpageView(id);
		return ServerResponse.getSuccess("SUCCESS", graduated);
	}

	// 查询所有的就业信息倒序
	@Override
	public ServerResponse getallgraduated() {
		List<Graduated> graduateds = graduatedDao.getallgraduated();
		PageInfo<Graduated> inif = new PageInfo<Graduated>(graduateds);
		Map<String, Object> data = new HashMap<>();
		data.put("total", inif.getTotal());
		data.put("data", graduateds);
		return ServerResponse.getSuccess("SUCCESS", data);
	}

	// 维护索引库
	@Override
	public Code updateSolr() {
		Code code = graduatedSearch.updateSolr();
		return code;
	}

	// 判断Token是否失效
	public Boolean isToken(String token) {
		System.out.println("------页面的tokenaasd------"+token);
		if (token != null) {
			// redis 获取验证码
			System.out.println("------页面的token------"+token);
			String redistoken = redisService.hget("APPLOGIN", token);
			System.out.println("------redis的token------"+redistoken);
			if (redistoken != null) {
				return true;
			}
		}
		return false;
	}

	
	@Override
	public ServerResponse addStuAndProxyTeacher(String studentName, String phone, String teacherName,String teacherPhone,String remark) {
		studentProxyTeacherDao.addStuAndProxyTeacher(studentName, phone, teacherName,teacherPhone,remark);
		return  ServerResponse.getSuccess("添加成功");
		
	}
	//添加招生老师的微信信息并绑定
	@Override
	public ServerResponse addProxyTeacherWatchInfo(String openid,String nickname,Integer sex,String city,String country,String province,String appTel,String code) {
		ProxyTeacherWatch proxyTeacherWatch = new ProxyTeacherWatch(openid,nickname,sex,city,country,province);
		proxyTeacherDao.addProxyTeacherWatchInfo(proxyTeacherWatch);
		// 判断是否为空
		if(appTel == null){
			return ServerResponse.getError("请输入用户名");
		}
		if(code == null){
			return ServerResponse.getError("验证码不能为空");
		}
		String redisCode = redisService.get(appTel);
		//System.out.println("----------redisCode------------"+redisCode);
		if(redisCode.equals(code)){
			ProxyTeacher proxyTeacher = proxyTeacherDao.getProxyTeacherByTel2(appTel);
			
			proxyTeacherDao.boundWatch(proxyTeacher.getId(),proxyTeacherDao.selectIdByOpenID(openid));
		}
		return ServerResponse.getError("绑定成功");
	}
//登录
	@Override
	public ServerResponse appUserLogin(String tel,String code) {
		if(tel == null){
			return ServerResponse.getError("请输入用户名");
		}
		ProxyTeacher proxyTeacher = proxyTeacherDao.getProxyTeacherByTel2(tel);
		// 判断手机号是否存在
		if (proxyTeacher == null) {
			return ServerResponse.getError("该用户不存在");
		}
		//根据手机号查询app_user表
		AppUsers appusers = appUsersDao.getByTel(tel);
		AppUsers appusersNew = new AppUsers();
		if(appusers == null){
			// 当没有这个用户时 手动创建此用户
			
			String appName = getAppName();
			appusersNew.setAppName(appName);
			appusersNew.setAppPassword("123456");
			appusersNew.setAppTel(proxyTeacher.getTel());
			appusersNew.setProxyTeacherId(proxyTeacher.getId());
			appUsersDao.addAppUser(appusersNew);
		}
		
		//根据Key从缓存中获取验证码
		String redisCode = redisService.get(tel);
		//System.out.println("----------redisCode------------"+redisCode);
		//判断两个验证码是否匹配
		if(redisCode.equals(code)){
			
				return getToken(proxyTeacher, tel, appusersNew);
		}
		return ServerResponse.getError("验证码错误");
	}
	//添加推荐人信息
	@Override
	public ServerResponse addReferrer(Referrer referrer) {
		referrerDao.addReferrer(referrer);
		return ServerResponse.getSuccess("添加成功");
	}
	
	// 学生姓名模糊查询 
	@Override
	public ServerResponse likeStuName(String stuName,String token) {
		// TODO Auto-generated method stub
		Integer proxyId =Integer.parseInt(redisService.hget("APPLOGIN", token)) ;
		List<Student> name = studentDao.likeStuName(stuName,proxyId);
		return ServerResponse.getSuccess("SUCCESS", name);
	}

	
	@Override
	public ServerResponse getGradutionPresonList() {
		
		List<FindJodPreson> presonList = gradutionPresonDao.getGradutionPresonList();
		System.err.println(presonList);
		Map<String,List<FindJodPreson>> map = new HashMap<>();
		List<FindJodPreson> list = new ArrayList<>();
		String addressStr = "";
		System.err.println(addressStr);
		for (FindJodPreson findJodPreson : presonList) {
			String address = findJodPreson.getAddress();
			addressStr = address.substring(0, 7);
			list = gradutionPresonDao.getFindJodPresonByAddress(addressStr);
			map.put(addressStr, list);
			
		}
		
		//遍历
//				Set<String> keySet = map.keySet();
//				for(String key:keySet){
//					System.out.println(key);
//					List<FindJodPreson> val = map.get(key);
//					for(FindJodPreson eve:val){
//						System.out.println("\t"+eve);
//					}
//				}
		
		
		return ServerResponse.getSuccess("SUCCESS", map);
	}

	
	
	
	//依据登陆人id匹配自己的学生的1，3，6个月的考勤信息
	@Override
	public ServerResponse getStudentCheckingInByUserId(String token) throws Exception {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		//从token取登陆人id
		String id = redisService.hget("APPLOGIN", token);
		Integer userId = Integer.parseInt(id);
		//根据id获取其对应的学生id集合
		List<Student> studentList = studentDao.getStudentCheckingInByUserId(userId);
		//定义返回值
		Map map = new HashMap<>();
		for (Student student : studentList) {
			Integer studentId = student.getId();
			
			List<QueryVo2> lqy = new ArrayList<QueryVo2>();
			QueryVo2 qy1 = new QueryVo2();
			QueryVo2 qy2 = new QueryVo2();
			QueryVo2 qy3 = new QueryVo2();

			Student attenceByStudentId = studentDao.getAttenceByStudentId(studentId);
			// 入学时间
			String joinTime = attenceByStudentId.getJoinTime();
			// 该学生的 钉钉唯一标识符
			String dingId = attenceByStudentId.getDingId();
			// 学生姓名
			String name = attenceByStudentId.getName();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			// System.out.println(joinTime);
			Date d1 = df.parse(joinTime);
			long daysBetween = (date.getTime() - d1.getTime()) / (60 * 60 * 24 * 1000); // 入学时间与现在时间的间隔
			if (daysBetween < 90) {
				// 一个月的考勤信息
				List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);

				AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
				attenceCount.setStudentName(name);
				// attenceCounts1.add(attenceCount);
				qy1.setStringWork("最近一个月");
				qy1.setAttenceCount(attenceCount);
				lqy.add(qy1);
			} else if (daysBetween > 90 & daysBetween < 180) {
				// 一个月的考勤信息
				List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);

				AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
				attenceCount.setStudentName(name);
				// attenceCounts1.add(attenceCount);
				qy1.setStringWork("最近一个月");
				qy1.setAttenceCount(attenceCount);
				lqy.add(qy1);
				// 三个月的考勤信息
				List<AttenceGroup> attenceCount2 = attenceDao.getAttenceCount2(dingId);

				AttenceCount attenceCount3 = getAttenceByStudentId2(attenceCount2);
				attenceCount3.setStudentName(name);
				// attenceCounts2.add(attenceCount3);
				qy2.setStringWork("最近三个月");
				qy2.setAttenceCount(attenceCount3);
				lqy.add(qy2);
			} else if (daysBetween > 180) {
				// 一个月的考勤信息
				List<AttenceGroup> attenceCount1 = attenceDao.getAttenceCount1(dingId);
				// AttenceCount attenceCount = new AttenceCount();
				// for (AttenceGroup attenceGroup : attenceCount1) {
				// if(attenceGroup.getTimeResult() == 0){
				// attenceCount.setNormal(attenceGroup.getAmount());
				// }else if(attenceGroup.getTimeResult() == 1){
				// attenceCount.setEarly(attenceGroup.getAmount());
				// }else if(attenceGroup.getTimeResult()==2){
				// attenceCount.setLate(attenceGroup.getAmount());
				// }else if(attenceGroup.getTimeResult()==3){
				// attenceCount.setSeriousLate(attenceGroup.getAmount());
				// }else if(attenceGroup.getTimeResult()==4){
				// attenceCount.setAbsenteeism(attenceGroup.getAmount());
				// }else if(attenceGroup.getTimeResult()==5){
				// attenceCount.setNotSigned(attenceGroup.getAmount());
				// }
				// }
				AttenceCount attenceCount = getAttenceByStudentId2(attenceCount1);
				attenceCount.setStudentName(name);
				// attenceCounts1.add(attenceCount);
				qy1.setStringWork("最近一个月");
				qy1.setAttenceCount(attenceCount);
				lqy.add(qy1);
				// 三个月的考勤信息
				List<AttenceGroup> attenceCount2 = attenceDao.getAttenceCount2(dingId);

				AttenceCount attenceCount3 = getAttenceByStudentId2(attenceCount2);
				attenceCount3.setStudentName(name);
				// attenceCounts2.add(attenceCount3);
				qy2.setStringWork("最近三个月");
				qy2.setAttenceCount(attenceCount3);
				lqy.add(qy2);
				// 六个月的考勤信息
				List<AttenceGroup> attenceCount4 = attenceDao.getAttenceCount2(dingId);

				AttenceCount attenceCount5 = getAttenceByStudentId2(attenceCount4);
				attenceCount5.setStudentName(name);
				// attenceCounts3.add(attenceCount5);
				// mla.put("最近六个月", attenceCounts3);
				qy3.setStringWork("最近六个月");
				qy3.setAttenceCount(attenceCount5);
				lqy.add(qy3);
				map.put(student.getName(), lqy);
			}
		}
		return ServerResponse.getSuccess("SUCCESS", map);
	}
	
	@Override
	//根据学生姓名或者地区查询学生就业信息
	public ServerResponse getGraduated(String name, String address) {
		List<Student> studentList = studentDao.getGraduated(name,address);
		return ServerResponse.getSuccess("SUCCESS", studentList);

	}


/**
 * 根据用户的id查找学生信息	
 */
	@Override
	public ServerResponse getTokenIdFindStudent(String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		//从token取登陆人id
		String id = redisService.hget("APPLOGIN", token);
		Integer userId = Integer.parseInt(id);
		//根据id获取其对应的学生id集合
		List<Student> studentList = studentDao.getStudentCheckingInByUserId(userId);
		return ServerResponse.getSuccess("SUCCESS", studentList);
	}
	/**
	 * 根据用户id查找学生学费
	 */

	@Override
	public ServerResponse getTokenIdFindStuFee(String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		//从token取登陆人id
		String id = redisService.hget("APPLOGIN", token);
		Integer userId = Integer.parseInt(id);
		//根据id获取其对应的学生id集合
		Map<Student,List<Fee>> map = new HashMap<>();
		List<Student> studentList = studentDao.getStudentCheckingInByUserId(userId);
		for (Student student : studentList) {
			//获取到学生id 根据学生id查找对应的缴费记录
			
			List<Fee> fee = feeDao.selectFee(student.getId());
				map.put(student, fee);
		}
		String json = JSONUtil.toJSON(map);
		return ServerResponse.getSuccess("SUCCESS", json);
	}
	/**
	 * 根据用户id模糊查找学生学费
	 */
	@Override
	public ServerResponse getTokenIdLikeStuFee(String stuName,String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		//从token取登陆人id
		String id = redisService.hget("APPLOGIN", token);
		Integer userId = Integer.parseInt(id);
		//模糊查找学生
		Map<Student,List<Fee>> map = new HashMap<>();
		List<Student> student = studentDao.likeStuName(stuName,userId);
		for (Student student2 : student) {
			//获取到学生id 根据学生id查找对应的缴费记录
			 List<Fee> fee = feeDao.selectFee(student2.getId());
			map.put(student2, fee);
		}
		String json = JSONUtil.toJSON(map);
		System.out.println("json:"+json);
		return ServerResponse.getSuccess("SUCCESS", json);
	}

	@Override
	public ServerResponse getScoreByStudentId(Integer id, String token) {
		if (!isToken(token)) {
			return ServerResponse.getError("Token失效，请重新获取!");
		}
		List<Exam> examByStudentId = examDao.getExamByStudentId(id);
		List<Score> score = new ArrayList<Score>();
		if (examByStudentId != null) {
			for (Exam exam : examByStudentId) {
				Integer id2 = exam.getId();
				List<Score> score2 = scoreDao.getScoresByExamId(id2);
				for (Score score3 : score2) {
					score.add(score3);
				}
			}
			System.err.println("1234567890"+score.toString());
			/*PageInfo<Exam> inif = new PageInfo<Exam>(examByStudentId);
			Map<String, Object> data = new HashMap<>();
			data.put("total", inif.getTotal());
			data.put("rows", score);*/
			return ServerResponse.getSuccess("success", score);
		}
		return ServerResponse.getError("该生无考试记录!");
	}

	// 根据学生id查询学生就业信息
@Override
public ServerResponse getGraduatedByStudentId(Integer id) {
	Graduated gtaduate=graduatedDao.getGraduatedByStudentId(id);
	return ServerResponse.getSuccess("SUCCESS", gtaduate);
}

}

	
	

