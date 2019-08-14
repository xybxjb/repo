package cn.deepcoding.service.impl;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.deepcoding.dao.AppUsersDao;
import cn.deepcoding.dao.AttenceDao;
import cn.deepcoding.dao.ExamDao;
import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.GraduatedDao;
import cn.deepcoding.dao.GraduatedSearch;
import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.ScoreDao;
import cn.deepcoding.dao.StudentConversationDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.AppUsers;
import cn.deepcoding.model.Attence;
import cn.deepcoding.model.AttenceCount;
import cn.deepcoding.model.AttenceGroup;
import cn.deepcoding.model.Code;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.Graduated;
import cn.deepcoding.model.GraduatedResult;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.QueryVo2;
import cn.deepcoding.model.Student;
import cn.deepcoding.model.StudentConversation;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.redis.RedisService;
import cn.deepcoding.service.AppService;
import cn.deepcoding.util.ConstUtils;
import cn.deepcoding.util.MessageXsendDemo;

@Service
public class AppServiceIpml implements AppService {

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

	// 根据 招生老师 id 查询自己信息
	@Override
	public ProxyTeacher getProxyTeacher(Integer id) {
		ProxyTeacher parentProxyTeacher = proxyTeacherDao.getParentProxyTeacher(id);
		return parentProxyTeacher;
	}

	// 根据 招生老师 id 查询上级信息
	@Override
	public ProxyTeacher getParentProxyTeacher(Integer id) {
		Integer parentProxyTeacherId2 = proxyTeacherDao.getParentProxyTeacherId2(id);
		if (parentProxyTeacherId2 != null) {
			ProxyTeacher parentProxyTeacher2 = proxyTeacherDao.getParentProxyTeacher(parentProxyTeacherId2);
			Integer id2 = parentProxyTeacher2.getId();
			// System.err.println(name);
			int studentCount = studentDao.getStudentCountByProxyTeacherId(id2);
			parentProxyTeacher2.setStudentCount(studentCount);
			return parentProxyTeacher2;
		} else {
			return null;
		}

	}

	// 根据招生老师 id 查询下级信息
	@Override
	public List<ProxyTeacher> getSublevelProxyTeacher(Integer id) {
		List<ProxyTeacher> sublevelProxyTeacher = proxyTeacherDao.getSublevelProxyTeacher(id);
		if (sublevelProxyTeacher != null) {
			for (ProxyTeacher proxyTeacher : sublevelProxyTeacher) {
				Integer id2 = proxyTeacher.getId();
				int studentCount = studentDao.getStudentCountByProxyTeacherId(id2);
				proxyTeacher.setStudentCount(studentCount);
				System.out.println(proxyTeacher);
			}
			return sublevelProxyTeacher;
		} else {
			return null;
		}
	}

	// 根据招生老师 id 查询学生信息
	@Override
	public PageData getStudentByProxyTeacherId2(Integer id, Page page) {
		// 获取学生信息
		List<Student> studentByProxyTeacherId2 = studentDao.getStudentByProxyTeacherId2(id, page);
		// 学生信息个数
		int studentCountByProxyTeacherId = studentDao.getStudentCountByProxyTeacherId(id);

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
			pageDate.setTotal(studentCountByProxyTeacherId);
			return pageDate;
		} else {
			return null;
		}
	}

	// 根据学生 id 查询学生信息
	@Override
	public Student getStudentById3(Integer id) {
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
		return studentById3;
	}

	// 根据学生 id 查询学生缴费信息
	@Override
	public List<Fee> getFeeByStudentId2(Integer id) {
		List<Fee> feeByStudentId2 = feeDao.getFeeByStudentId2(id);
		return feeByStudentId2;
	}

	// 根据学生 id 查询 钉钉唯一标识符并获取考勤信息
	@Override
	public List<QueryVo2> getAttenceByStudentId1(Integer id) throws Exception {
		// Map<String,List<AttenceCount>> mla = new
		// HashMap<String,List<AttenceCount>>();
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
		return lqy;
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
	public List<AttenceCount> getAttenceByStudent1(Integer id) {
		// 根据学生 id 查询学生部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);
		// 该学生的 钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();

		// 根据 钉钉唯一标识符 查询最近一个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate1(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);

		return etAttenceByStudent;
	}

	// 根据学生查询 最近三个月的考勤明细
	public List<AttenceCount> getAttenceByStudent2(Integer id) {

		// 根据学生 id 查询部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);

		// 获取学生 的钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();
		// 根据 钉钉唯一标识符 查询最近三个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate2(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);
		return etAttenceByStudent;
	}

	// 查询学生最近六个月的明细表
	public List<AttenceCount> getAttenceByStudent3(Integer id) {
		// 根据学生 id 查询部分信息
		Student attenceByStudentId = studentDao.getAttenceByStudentId(id);
		// 获取学生 的钉钉唯一标识符
		String userId = attenceByStudentId.getDingId();
		// 根据 钉钉唯一标识符 查询最近三个月的日期
		List<Attence> attenceWorkDate1 = attenceDao.getAttenceWorkDate3(userId);
		List<AttenceCount> etAttenceByStudent = etAttenceByStudent(attenceWorkDate1, attenceByStudentId);
		return etAttenceByStudent;
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
	public List<Exam> getExamByStudentId(Integer id) {
		List<Exam> examByStudentId = examDao.getExamByStudentId(id);
		if (examByStudentId != null) {
			for (Exam exam : examByStudentId) {
				Integer id2 = exam.getId();
				Integer scoreByExamId = scoreDao.getScoreByExamId(id2);
				exam.setScore(scoreByExamId);
				System.out.println(exam);
			}
			return examByStudentId;
		} else {
			return null;
		}
	}

	// 根据学生id查询学生谈话记录
	@Override
	public List<StudentConversation> getStudentConversationById(Integer id) {
		List<StudentConversation> studentConversation = studentConversationDao.getById2(id);
		return studentConversation;
	}

	// 根据学生id 查询 谈话记录详细表（图片）
	@Override
	public String getStudentConversationPicById(Integer id) {
		String pic = studentConversationDao.getPicById(id);
		// 图片路径
		String string = ConstUtils.getString("image_url");
		return string + pic;
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
	public List<Graduated> getElite() {
		List<Graduated> elite = graduatedDao.getElite();
		return elite;
	}

	// 根据 账号 查询 信息
	@Override
	public Code getByName(String appTel, String appPassword, String tok) {
		Code c = new Code();
		// 判断是否输入手机号,就是根据 Token来判断是否有该用户
		if (appTel == null & tok != null) {
			String id = redisService.hget("APPLOGIN", tok);
			if (id != null) {
				AppUsers appUsers = appUsersDao.getAppuserById(Integer.parseInt(id));
				c.setStatus("0");
				c.setCode("SUCCESS");
				c.setMessage("含有 token登陆成功");
				c.setAppUsers(appUsers);
				return c;
			}
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("token失效");
			return c;

		}
		// 判断是否为空
		if (appTel == null || appPassword == null) {
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("手机号或密码不能为空");
			return c;
		}
		ProxyTeacher proxyTeacher = proxyTeacherDao.getProxyTeacherByTel2(appTel);
		// 判断手机号是否存在
		if (proxyTeacher == null) {
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("您的手机号不存在!!");
			return c;
		}
		// 判断用户表内是否有这个用户信息
		AppUsers appusers = appUsersDao.getByTel(appTel);
		if (appusers != null) {
			if (!appusers.getAppTel().equals(appTel) && !appusers.getAppPassword().equals(appPassword)) {
				c.setStatus("1");
				c.setCode("ERROR");
				c.setMessage("账号或密码不正确");
				return c;
			}
			return getToken(proxyTeacher, appTel, appusers);
		}
		if (!appPassword.equals("123456")) {
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("您的初始密码错误!!!");
			return c;
		}

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
	public Code getToken(ProxyTeacher proxyTeacher, String appTel, AppUsers appusers) {
		// 首先删除上次用户信息,防止用户重复登陆
		String tokenOld = redisService.hget("APPLOGIN", appTel);
		if (tokenOld != null) {
			redisService.hdel("APPLOGIN", tokenOld);
		}
		Code code = new Code();
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
		code.setAppUsers(appusers);
		code.setStatus("0");
		code.setCode("SUCCESS");
		code.setMessage("登陆成功");
		// 客户端的tooken
		code.setData(token);
		return code;
	}

	// 发送短信
	@Override
	public Code getMessage(String tel, HttpServletRequest request) {
		AppUsers byTel = appUsersDao.getByTel(tel);
		Code c = new Code();
		if (byTel != null) {
			AppUsers appUser = new AppUsers();
			// 生成 六位 验证码
			String getrandom2 = getrandom2();
			System.err.println(getrandom2);
			// 将验证码与短信调用发送短信接口
			MessageXsendDemo.getmessige(tel, Integer.parseInt(getrandom2));
			// 将随机数放到该session域中
			request.getSession().setAttribute("vcode", getrandom2);
			// 保存到 redis
			redisService.set(tel, getrandom2);
			redisService.expire(tel, 300);
			// 设置session存在时间为10分钟
			request.getSession().setMaxInactiveInterval(10 * 60);

			c.setStatus("0");
			c.setCode("SUCCESS");
			c.setMessage("发送成功");
		} else {
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("手机号不正确");
		}
		return c;

	}

	// 修改密码
	@Override
	public Code updatePassword(AppUsers appUsers, HttpServletRequest request) {
		Code c = new Code();
		// 短信发送的 验证码
		// String attribute = (String)
		// request.getSession().getAttribute("vcode");
		String appTel = appUsers.getAppTel();
		// redis 获取验证码
		String string = redisService.get(appTel);
		System.err.println(string);
		// 页面传过来的验证码
		String securityode = appUsers.getSecurityode();
		System.err.println(securityode);
		if (securityode.equals(string)) {
			appUsersDao.updatePassword(appUsers);
			c.setStatus("0");
			c.setCode("SUCCESS");
			c.setMessage("修改成功");
			redisService.del(appTel);
		} else {
			c.setStatus("1");
			c.setCode("ERROR");
			c.setMessage("验证码不正确");
		}
		return c;
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
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String date1 = "2018-12-26";
		String format = df.format(date);
		Date d1 = df.parse(format);
		Date d2 = df.parse(date1);
		long daysBetween = (d1.getTime() - d2.getTime()) / (60 * 60 * 24 * 1000);
		System.out.println(format);
		System.out.println(d1);
		System.out.println(daysBetween);
		// 图片路径
		String string = ConstUtils.getString("image_url");
		System.out.println(string);
		int i = (int) (15 + Math.random() * (20 - 15 + 1));
		System.out.println(i);
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
	public Graduated getGraduatedById(Integer id) {
		Graduated graduated = graduatedDao.getGraduatedById(id);
		graduatedDao.updateStudentpageView(id);
		return graduated;
	}

	// 查询所有的就业信息倒序
	@Override
	public List<Graduated> getallgraduated() {
		List<Graduated> graduateds = graduatedDao.getallgraduated();
		return graduateds;
	}

	// 维护索引库
	@Override
	public Code updateSolr() {
		Code code = graduatedSearch.updateSolr();
		return code;
	}

}
