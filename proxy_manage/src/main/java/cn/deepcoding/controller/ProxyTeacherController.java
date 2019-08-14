package cn.deepcoding.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherPoint;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.ProxyTeacherService;

@Controller

@RequestMapping("/proxyTeacher")
public class ProxyTeacherController {
	@Autowired
	private ProxyTeacherService proxyTeacherService;
	@Autowired
	private HttpServletRequest request;

	@RequiresPermissions("proxyTeacher:index")
	@RequestMapping("/index")
	public String index() {
		return "proxyTeacher/proxyTeacher";
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageData get(ProxyTeacher proxyTeacher, Page page) {
		PageData findByPage = proxyTeacherService.findByPage(proxyTeacher, page);
		return findByPage ;
	}

	@RequestMapping("add")
	public String add(@RequestParam("file") MultipartFile file, ProxyTeacher proxyTeacher) {
		proxyTeacherService.add(proxyTeacher, file);
		return "proxyTeacher/proxyTeacher";

	}

	// 根据老师id和点位表id修改点位表数据
	@RequestMapping("/updatepoint")
	@ResponseBody
	public String updatepoint(ProxyTeacherPoint proxyteacherpoint) {
		return proxyTeacherService.updatepoint(proxyteacherpoint);
	}

	// 添加点位表数据
	@RequestMapping("/addpoint")
	@ResponseBody
	public String addpoint(HttpSession httpSession, ProxyTeacherPoint proxyteacherpoint, HttpServletRequest request) {

		System.err.println("HelloWorld" + proxyteacherpoint);
		// 获取session域中的招生老师id
		Object ProxyTeacherIdd = httpSession.getAttribute("ProxyTeacherId");
		// 将招生老师id转换为int类型
		int ProxyTeacherId = Integer.parseInt(String.valueOf(ProxyTeacherIdd));
		// 将招生老师id放到招生老师的点位表中
		proxyteacherpoint.setProxyTeacherId(ProxyTeacherId);
		// 进行添加操作
		return proxyTeacherService.addpoint(proxyteacherpoint, request);

	}

	// 详情 //根据招生老师id 和点位数据的id 查询出相应的点位数据
	@RequestMapping("/selectpointbyid")
	@ResponseBody
	public ProxyTeacherPoint selectpointbyid(HttpSession httpsession, Integer id) {
		System.err.println("newnewnewnewnew");
		System.err.println("id+++++++++" + id);
		// 获取session域中的招生老师id
		Object ProxyTeacherIdd = httpsession.getAttribute("ProxyTeacherId");
		// 将招生老师id转换为int类型
		int ProxyTeacherId = Integer.parseInt(String.valueOf(ProxyTeacherIdd));
		// 进行添加操作

		// 根据招生老师id 和点位数据的id 查询出相应的点位数据

		return proxyTeacherService.selectpointbyid(id, ProxyTeacherId);

	}

	// 根据id删除招生老师中的点位数据
	@RequestMapping("/deletepoint")
	public String deletepoint(HttpSession httpsession, Integer id) {
		System.err.println("id+++++++++" + id);
		// 获取session域中的招生老师id
		Object ProxyTeacherIdd = httpsession.getAttribute("ProxyTeacherId");
		// 将招生老师id转换为int类型
		int ProxyTeacherId = Integer.parseInt(String.valueOf(ProxyTeacherIdd));
		// 进行删除操作
		proxyTeacherService.deletepoint(id, ProxyTeacherId);
		return "proxyTeacher/proxyTeacher";

	}

	// 根据招生老师id查询点位表中的数据
	@RequestMapping("/getpoint")
	@ResponseBody
	public List<ProxyTeacherPoint> getpoint(HttpSession httpSession, Integer id) {
		httpSession.setAttribute("ProxyTeacherId", id);

		// 将招生老师字段中的point数据添加到poin表的list集合
		// ProxyTeacher proxyTeacher = proxyTeacherService.get(id);
		// Double point = proxyTeacher.getPoint();
		List<ProxyTeacherPoint> list = proxyTeacherService.getpoint(id);
		// list.add(new proxyteacherpoint(null, point, null, null, null, null,
		// null));

		return list;
	}

	@RequestMapping("savePic")
	@ResponseBody
	public String savePic(@RequestParam(value = "file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String path = request.getSession().getServletContext().getRealPath("");
				path = path.replaceAll("proxy_manage", "");
				path = path.substring(0, path.length() - 1);
				File newFile = new File(path + "images/bankPic/");
				if (!newFile.exists())
					newFile.mkdirs();
				String name = file.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."), name.length());
				String uuid = UUID.randomUUID().toString();
				String filePath = path + "images/bankPic/" + uuid + wei;
				// 转存文件
				file.transferTo(new File(filePath));
				// //imUurl路径
				// String imgUrl = ImagUtils.imgUrl();
				return "/bankPic/" + uuid + wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}

	@RequestMapping("/delete")
	public String delete(Integer id) {
		proxyTeacherService.delete(id);
		return "proxyTeacher/proxyTeacher";
	}

	@RequestMapping("get")
	@ResponseBody
	public ProxyTeacher get(Integer id) {
		return proxyTeacherService.get(id);
	}

	@RequestMapping("update")
	public String update(@RequestParam("file") MultipartFile file, ProxyTeacher proxyTeacher) {

		proxyTeacherService.update(proxyTeacher, file);
		return "proxyTeacher/proxyTeacher";

	}

	@RequestMapping("getAll")
	@ResponseBody
	public List<ProxyTeacher> getAll() {
		return proxyTeacherService.getAll();
	}

	@RequestMapping("delState")

	public String delState(ProxyTeacher proxyTeacher) {
		proxyTeacherService.deljia(proxyTeacher);
		return "proxyTeacher/proxyTeacher";

	}

	@RequestMapping("getRankParent")
	@ResponseBody
	public List<ProxyTeacher> getRankParent(Integer rankId) {
		return proxyTeacherService.getRankProxteacher(rankId);
	}

}
