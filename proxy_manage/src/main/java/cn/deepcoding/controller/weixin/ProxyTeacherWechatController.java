package cn.deepcoding.controller.weixin;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Fee;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherWechatService;

@Controller
@RequestMapping("/proxyTeacherWechat")
public class ProxyTeacherWechatController {
	@Autowired
	private ProxyTeacherWechatService proxyTeacherWechatService;

	// 根据招生老师 手机号获取信息
	@RequestMapping("/getProxyTeacherByTel")
	@ResponseBody
	public Map<String, Object> getProxyTeacherByTel(String tel) {
		if (tel == null) {
			return null;
		}
		Map<String, Object> proxyTeacherByTel = proxyTeacherWechatService.getProxyTeacherByTel(tel);
		if (proxyTeacherByTel == null) {
			return null;
		} else {
			for (Map.Entry<String, Object> entry : proxyTeacherByTel.entrySet()) {
				System.out.println("key=" + entry.getKey() + " : " + "value=" + entry.getValue());
			}
			return proxyTeacherByTel;
		}
	}

	// 获取招生老师的手机号
	@RequestMapping("/getProxyTeacherTel")
	@ResponseBody
	public String getProxyTeacherTel(HttpServletRequest request) {
		if ((String) request.getSession().getAttribute("tel") != null) {
			return (String) request.getSession().getAttribute("tel");
		}
		return null;
	}

	// 获取招生老师的id
	@RequestMapping("/getProxyTeacherID")
	@ResponseBody
	public Integer getProxyTeacherID(HttpServletRequest request) {
		if ((Integer) request.getSession().getAttribute("proxy_manageId") != null) {
			System.err.println("招生老师ID" + (Integer) request.getSession().getAttribute("proxy_manageId"));
			return (Integer) request.getSession().getAttribute("proxy_manageId");
		}
		return null;
	}

	// 根据招生老师 id 获取学生信息
	@RequestMapping("/getStudentByProxyTeacherId")
	@ResponseBody
	public PageData getStudentByProxyTeacherId(Integer proxyTeacherId, Page page) throws Exception {
		/*
		 * page.setPage(1); page.setRows(10);
		 */
		if (proxyTeacherId == null) {
			return null;
		}
		PageData studentByProxyTeacherId = proxyTeacherWechatService.getStudentByProxyTeacherId(proxyTeacherId, page);
		if (studentByProxyTeacherId.getRows().isEmpty()) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> rows = (List<Map<String, Object>>) studentByProxyTeacherId.getRows();
			for (Map<String, Object> map : rows) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					System.out.println("key=" + entry.getKey() + " : " + "value=" + entry.getValue());
				}
			}
			return studentByProxyTeacherId;
		}
	}

	// 根据学生 id 查询学生信息
	@RequestMapping("/getById")
	@ResponseBody
	public Map<String, Object> getStudentById2(Integer studentId) throws Exception {
		if (studentId == null) {
			return null;
		}
		Map<String, Object> studentById2 = proxyTeacherWechatService.getStudentById2(studentId);
		if (studentById2 == null) {
			return null;
		} else {
			Set<Entry<String, Object>> entrySet = studentById2.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				// 未交学费的null改为0
				if (entry.getKey().equals("amountTuition") & entry.getValue() == null) {
					entry.setValue(0.0);
				}
			}
			return studentById2;
		}
	}

	// 根据学生 id 查询缴费详情
	@RequestMapping("/selectFee")
	@ResponseBody
	public PageData select(Integer studentId, Page page) {
		if (studentId == null) {
			return null;
		}
		PageData select = proxyTeacherWechatService.select(studentId, page);
		if (select == null) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			List<Fee> rows = (List<Fee>) select.getRows();
			for (Fee fee : rows) {
				System.err.println(fee);
				System.out.println("缴费金额:" + fee.getAmount());
				System.out.println("缴费时间:" + fee.getPaymentTime());
				System.out.println("缴费方式:" + fee.getPayment().getName());
			}
			return select;
		}
	}
}
