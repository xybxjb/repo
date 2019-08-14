package cn.deepcoding.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.deepcoding.dao.ProxyTeacherDao;
import cn.deepcoding.dao.ProxyTeacherWatchDao;
import cn.deepcoding.dao.ProxyTeacheridAndWatchidDao;
import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherIdAndWatchId;
import cn.deepcoding.model.ProxyTeacherWatch;
import cn.deepcoding.service.ProxyTeacherWatchService;
import cn.deepcoding.util.MessageXsendDemo;
import cn.deepcoding.util.WeixinUtil;

@Service
@Transactional
public class ProxyTeacherWatchServiceImpl implements ProxyTeacherWatchService {
	@Autowired
	private ProxyTeacherWatchDao proxyteacherdao;
	@Autowired
	private ProxyTeacherDao proxyTeacherDao;
	@Autowired
	private ProxyTeacheridAndWatchidDao proxyteacheridandwatchid;

	// 判断改oppenid是否在数据库存在，并且能查询出关联信息
	@Override
	public boolean watchLogin(String oppenid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ProxyTeacherWatch findProxyTeacherWatchByOppenid = proxyteacherdao.FindProxyTeacherWatchByOppenid(oppenid);
		/**
		 * 判断能够查到招生老师与微信关联表 获取到招生老师的ID与手机号放入session域内
		 */
		request.getSession().setAttribute("oppenid", oppenid);
		if (findProxyTeacherWatchByOppenid != null) {
			// 如果未查询出关联表直接返回
			if (findProxyTeacherWatchByOppenid.getProxyteacheridandwatchid() == null) {
				return false;
			}
			System.err.println(findProxyTeacherWatchByOppenid);
			Integer proxyTeacherid = findProxyTeacherWatchByOppenid.getProxyteacheridandwatchid().getProxyteacheer()
					.getId();
			String tel = findProxyTeacherWatchByOppenid.getProxyteacheridandwatchid().getProxyteacheer().getTel();
			// 把手机号码放入session域
			request.getSession().setAttribute("tel", tel);
			// 把招生老师ID放入session域
			request.getSession().setAttribute("proxy_manageId", proxyTeacherid);
			request.getSession().setAttribute(tel, proxyTeacherid);
			return true;
		}
		return false;
	}
	// 发送短信
	@Override
	public Map<String, String> getmessige(String tel, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> hashMap = new HashMap<String, String>();

		Map<String, Object> proxyTeacherByTel = proxyTeacherDao.getProxyTeacherByTel(tel);
		if (proxyTeacherByTel != null) {
			hashMap.put("status", "0");
			hashMap.put("code", "SUCCESS");
			hashMap.put("message", "发送成功");
			// 生成验证码
			String vcode = getrandom();
			System.out.println(vcode);
			// 将验证码与短信调用发送短信接口
			 MessageXsendDemo.getmessige(tel, Integer.parseInt(vcode));
			// 将随机数放到该session域中
			request.getSession().setAttribute("vcode", vcode);
			// 设置session存在时间为10分钟
			request.getSession().setMaxInactiveInterval(10 * 60);
			// 将招生老师ID赋值给 OPPENid
			String oppenid = (String) request.getSession().getAttribute("oppenid");
			if (oppenid != null) {
				request.getSession().setAttribute(oppenid, proxyTeacherByTel.get("id"));
			}
			// 把手机号码放入session域
			request.getSession().setAttribute("tel", tel);
			return hashMap;
		}
		hashMap.put("status", "1");
		hashMap.put("code", "ERROR");
		hashMap.put("message", "手机号码输入有误，或您的信息未被录入");
		return hashMap;
	}

	// 生成六位随机数
	public String getrandom() {
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

	// 验证码接入验证
	@Override
	public Map<String, String> getcode(HttpServletRequest request, String code) {
		// TODO Auto-generated method stub
		Map<String, String> hashMap = new HashMap<String, String>();
		// 获取到session域内所存储的验证码
		String vcode = (String) request.getSession().getAttribute("vcode");
		// 获取到的验证码与session存储的验证码是否一致
		if (vcode.equals(code)) {
			System.err.println("session域的验证码：" + vcode + "输入的验证码:" + code);
			// 将用户登陆时候的oppenid获取到
			String oppenid = (String) request.getSession().getAttribute("oppenid");
			// 删除原有的oppenid信息
			proxyteacherdao.DeleteProxyTeacherWatchByoppenId(oppenid);
			// 获取到招生老师的ID
			Integer proteacherid = (Integer) request.getSession().getAttribute(oppenid);
			// 根据招生老师ID删除中间表
			proxyteacheridandwatchid.DeleteproxyteacherIdandwatchIdByproteacherId(proteacherid);
			if (oppenid != null) {
				ProxyTeacherWatch proxyTeacherWatch = new ProxyTeacherWatch();
				proxyTeacherWatch.setOpenid(oppenid);
				// 获取accesstiken
				WeixinUtil.getAccessToken();
				// 调用远程的oppenid获取用户信息
				String string = WeixinUtil.getUnionID(oppenid);
				JSONObject jsonObject = JSON.parseObject(string);
				// 昵称
				String name = (String) jsonObject.get("nickname");
				String bname = Base64.encodeBase64String(name.getBytes());
				proxyTeacherWatch.setNickname(bname);
				// 国家
				String country = (String) jsonObject.get("country");
				proxyTeacherWatch.setCountry(country);
				// 城市
				String city = (String) jsonObject.get("city");
				proxyTeacherWatch.setCity(city);
				// 省份
				String province = (String) jsonObject.get("province");
				proxyTeacherWatch.setProvince(province);
				// 性别
				Integer sex = (Integer) jsonObject.get("sex");
				proxyTeacherWatch.setSex(sex);
				// 将微信获取到的用户信息存入数据库
				proxyteacherdao.ProxyTeacherWatchAdd(proxyTeacherWatch);
				// 把招生老师的与微信号绑定
				ProxyTeacherIdAndWatchId proxyTeacherIdAndWatchId = new ProxyTeacherIdAndWatchId();
				ProxyTeacher proxyTeacher = new ProxyTeacher();
				// 将招生老师微信信息放入
				proxyTeacherIdAndWatchId.setProteacherwatch(proxyTeacherWatch);
				// 将招生老师信息放入
				proxyTeacher.setId(proteacherid);
				proxyTeacherIdAndWatchId.setProxyteacheer(proxyTeacher);
				// 将关联信息写入
				proxyteacheridandwatchid.proxyteacherIdandwatchIdAdd(proxyTeacherIdAndWatchId);
				// 把招生老师ID放入session域
				request.getSession().setAttribute("proxy_manageId", proteacherid);
			}
			hashMap.put("status", "0");
			hashMap.put("code", "SUCCESS");
			hashMap.put("message", "登陆成功");
			return hashMap;
		}
		hashMap.put("status", "1");
		hashMap.put("code", "ERROR");
		hashMap.put("message", "验证码错误");
		return hashMap;
	}
}
