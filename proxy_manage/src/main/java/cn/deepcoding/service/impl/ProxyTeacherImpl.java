package cn.deepcoding.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.constants.ProxyTeacherSource;
import cn.deepcoding.dao.ProxyTeacherDao;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherPoint;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherService;
import cn.deepcoding.util.DateUtil;
import cn.deepcoding.util.ImagUtils;

@Service
@Transactional
public class ProxyTeacherImpl implements ProxyTeacherService {

	@Autowired
	private ProxyTeacherDao proxyTeacherDao;
	@Autowired
	private HttpServletRequest request;

	@Override
	public void add(ProxyTeacher proxyTeacher, MultipartFile file) {
		// TODO Auto-generated method stub
		// if (!file.isEmpty()) {
		// try {
		// // 文件保存路径
		// String path =
		// request.getSession().getServletContext().getRealPath("");
		// path =path.replaceAll("proxy_manage","");
		// path=path.substring(0, path.length()-1);
		// File newFile = new File(path+"images//bankPic//");
		// if(!newFile.exists()) newFile.mkdirs();
		// String name =file.getOriginalFilename();
		// String wei = name.substring(name.lastIndexOf("."),name.length());
		// String uuid = UUID.randomUUID().toString();
		// String filePath = path+"images//bankPic//" + uuid+wei;
		// // 转存文件
		// file.transferTo(new File(filePath));
		// proxyTeacher.setBankcard("//bankPic//"+uuid+wei);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		String str = proxyTeacher.getAddress().replaceAll(",", "-");
		proxyTeacher.setAddress(str);
		proxyTeacher.setSource(ProxyTeacherSource.back);
		proxyTeacherDao.add(proxyTeacher);
		//System.err.println(proxyTeacher);
		ProxyTeacherPoint proxyTeacherPoint = new ProxyTeacherPoint();
		// 将录入到的日期初始化为1号
		System.out.println(proxyTeacher.getJoinDate());
			String startdate = proxyTeacher.getJoinDate().toString();
			System.out.println(startdate);
			Date date;
			Date date2;
			String startTime = null;
			String endTime = null;
			try {
				String enddate = DateUtil.getDateByConditions(new SimpleDateFormat("yyyy-MM-dd").parse(startdate), 12);
				// 将string类型转换为Double类型
				date = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
				startTime = DateUtil.getDateByConditions(date, 6);
				endTime = DateUtil.getDateByConditions(date2, 7);
				System.err.println("第一天：" + startTime);
				System.err.println("下一年的今天：" + endTime);
				// 判断录入的日期是否相同
				// 添加日期判断是否与之前的相同如果相同则不能添加进来
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proxyTeacherPoint.setStartDate(startTime);
			proxyTeacherPoint.setEndDate(endTime);
			proxyTeacherPoint.setProxyTeacherId(proxyTeacher.getId());
			proxyTeacherPoint.setPoint(proxyTeacher.getPoint());
			System.err.println(proxyTeacherPoint);
			proxyTeacherDao.addpoint(proxyTeacherPoint);
	}

	@Override
	public ProxyTeacher get(Integer id) {
		ProxyTeacher proxyTeacher = proxyTeacherDao.get(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		proxyTeacher.setBankcard(imgUrl + proxyTeacher.getBankcard());
		System.err.println("图片路径：" + proxyTeacher.getBankcard());
		return proxyTeacher;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		proxyTeacherDao.delete(id);
	}

	@Override
	public void update(ProxyTeacher proxyTeacher, MultipartFile file) {
		// TODO Auto-generated method stub
		// if (!file.isEmpty()) {
		// try {
		// // 文件保存路径
		// String path =
		// request.getSession().getServletContext().getRealPath("");
		// path =path.replaceAll("proxy_manage","");
		// path=path.substring(0, path.length()-1);
		// File newFile = new File(path+"images/bankPic/");
		// if(!newFile.exists()) newFile.mkdirs();
		// String name =file.getOriginalFilename();
		// String wei = name.substring(name.lastIndexOf("."),name.length());
		// String uuid = UUID.randomUUID().toString();
		// String filePath = path+"images/bankPic/" + uuid+wei;
		// // 转存文件
		// file.transferTo(new File(filePath));
		// proxyTeacher.setBankcard("/images/bankPic/"+uuid+wei);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		if (proxyTeacher.getAddress() != null && proxyTeacher.getAddress() != "") {
			String str = proxyTeacher.getAddress().replaceAll(",", "-");
			proxyTeacher.setAddress(str);

		}
		proxyTeacherDao.update(proxyTeacher);
	}

	@Override
	public ProxyTeacher getParentProxyTeacherId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProxyTeacher> getAll() {
		// TODO Auto-generated method stub
		return proxyTeacherDao.getAll();
	}

	@Override
	public PageData findByPage(ProxyTeacher proxyTeacher, Page page) {
		// TODO Auto-generated method stub
		int total = proxyTeacherDao.getTotal(proxyTeacher);
		List<ProxyTeacher> roles = proxyTeacherDao.getPageData(proxyTeacher, page);
		List<ProxyTeacher> proxyTeachers = proxyTeacherDao.find();
			
		for (ProxyTeacher proxyTeacher1 : proxyTeachers) {
//			System.err.println(proxyTeacher1.getProxyTeacherPoint().getStartDate()+"2345678o");
			String str = "";
			List<ProxyTeacher> list = new ArrayList<>();
			if (proxyTeacher1.getParentProxyTeacherId() != null) {
				list = parentProxyTeacher(list, proxyTeacher1.getParentProxyTeacherId().getId());
				for (ProxyTeacher pro : list) {
					String str1 = pro.getName() + ("-") + str;
					str = str1;
					pro.setLeaderName(str);
					for (ProxyTeacher proxy : roles) {
						if (proxy.getId() == proxyTeacher1.getId()) {
							if (pro.getLeaderName().endsWith("-")) {
								pro.setLeaderName(pro.getLeaderName().substring(0, pro.getLeaderName().length() - 1));
							}
							proxy.setLeaderName(pro.getLeaderName());
						}
					}
				}

			} else {
				continue;
			}
		}
		System.out.println("============"+Arrays.toString(roles.toArray()));
		PageData data = new PageData();

		data.setRows(roles);
		data.setTotal(total);
		return data;
	}

	public List<ProxyTeacher> parentProxyTeacher(List<ProxyTeacher> puisneTeachers, Integer pid) {
		ProxyTeacher proxyTeacher = new ProxyTeacher();
		if (pid != null) {
			proxyTeacher = proxyTeacherDao.getParentId(pid);
			if (proxyTeacher != null) {
				puisneTeachers.add(proxyTeacher);
				if (proxyTeacher.getParentProxyTeacherId() != null) {
					parentProxyTeacher(puisneTeachers, proxyTeacher.getParentProxyTeacherId().getId());
				} else {

				}

			}
		}
		return puisneTeachers;
	}

	@Override
	public List<ProxyTeacher> getById(Integer id) {
		// TODO Auto-generated method stub
		return proxyTeacherDao.getById(id);
	}

	@Override
	public void deljia(ProxyTeacher proxyTeacher) {
		// TODO Auto-generated method stub
		proxyTeacherDao.deljia(proxyTeacher);
	}

	@Override
	public List<ProxyTeacher> getRankProxteacher(Integer rankId) {
		// TODO Auto-generated method stub
		return proxyTeacherDao.getRankProxteacher(rankId);
	}

	@Override
	public List<ProxyTeacherPoint> getpoint(Integer id) {
		// TODO Auto-generated method stub
		return proxyTeacherDao.getpoint(id);
	}

	// 添加点位数据
	@Override
	public String addpoint(ProxyTeacherPoint proxyteacherpoint, HttpServletRequest request) {
		Integer ProxyTeacherId = proxyteacherpoint.getProxyTeacherId();
		// 根据添加得日期判断这个开始和结束日期是否已经存在
		Double Startpoint = proxyTeacherDao.getpointByTimeAndproxyTeacherId(proxyteacherpoint.getStartDate(),
				ProxyTeacherId, null);
		if (Startpoint != null) {
			return "StartTimeError";
		}
		Double Endpoint = proxyTeacherDao.getpointByTimeAndproxyTeacherId(proxyteacherpoint.getEndDate(),
				ProxyTeacherId, null);
		if (Endpoint != null) {
			return "EndTimeError";
		}
		// 判断录入得日期之间是否已有信息
		if (proxyTeacherDao.getpointByProxyTeacherPoint(proxyteacherpoint) > 0) {
			return "ContoinsTimeError";
		}
		// 将录入到的日期初始化为1号
		String startdate = proxyteacherpoint.getStartDate();
		String enddate = proxyteacherpoint.getEndDate();
		Date date;
		Date date2;
		String startTime = null;
		String endTime = null;
		try {
			// 将string类型转换为Double类型
			date = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
			startTime = DateUtil.getDateByConditions(date, 6);
			endTime = DateUtil.getDateByConditions(date2, 7);
			System.err.println("第一天：" + startTime);
			System.err.println("最后一天：" + endTime);
			// 判断录入的日期是否相同
			// 添加日期判断是否与之前的相同如果相同则不能添加进来
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proxyteacherpoint.setStartDate(startTime);
		proxyteacherpoint.setEndDate(endTime);
		proxyTeacherDao.addpoint(proxyteacherpoint);

		return "SUCCESS";

	}

	// 修改点位数据
	@Override
	public String updatepoint(ProxyTeacherPoint proxyteacherpoint) {
		Integer ProxyTeacherId = proxyteacherpoint.getProxyTeacher().getId();
		// 根据添加得日期判断这个开始和结束日期是否已经存在并且抛去他本身ID
		Double Startpoint = proxyTeacherDao.getpointByTimeAndproxyTeacherId(proxyteacherpoint.getStartDate(),
				ProxyTeacherId, proxyteacherpoint.getId());
		if (Startpoint != null) {
			return "StartTimeError";
		}
		Double Endpoint = proxyTeacherDao.getpointByTimeAndproxyTeacherId(proxyteacherpoint.getEndDate(),
				ProxyTeacherId, proxyteacherpoint.getId());
		if (Endpoint != null) {
			return "EndTimeError";
		}
		// 判断录入得日期之间是否已有信息
		if (proxyTeacherDao.getpointByProxyTeacherPoint(proxyteacherpoint) > 0) {
			return "ContoinsTimeError";
		}
		// 将录入到的日期初始化为1号
		String startdate = proxyteacherpoint.getStartDate();
		String enddate = proxyteacherpoint.getEndDate();
		Date date;
		Date date2;
		String startTime = null;
		String endTime = null;
		try {
			// 将string类型转换为Double类型
			date = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
			startTime = DateUtil.getDateByConditions(date, 6);
			endTime = DateUtil.getDateByConditions(date2, 7);
			System.err.println("第一天：" + startTime);
			System.err.println("最后一天：" + endTime);
			// 判断录入的日期是否相同
			// 添加日期判断是否与之前的相同如果相同则不能添加进来
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proxyteacherpoint.setStartDate(startTime);
		proxyteacherpoint.setEndDate(endTime);
		proxyTeacherDao.updatepoint(proxyteacherpoint);
		return "SUCCESS";

	}

	@Override
	public void deletepoint(Integer id, int proxyTeacherId) {
		proxyTeacherDao.deletepoint(id, proxyTeacherId);

	}

	@Override
	public ProxyTeacherPoint selectpointbyid(Integer id, int proxyTeacherId) {
		// TODO Auto-generated method stub
		return proxyTeacherDao.selectpointbyid(id, proxyTeacherId);
	}

}
