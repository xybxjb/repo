package cn.deepcoding.controller.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.deepcoding.model.Graduated;
import cn.deepcoding.service.AppServiceNew;
import io.swagger.annotations.Api;

@Controller
@RequestMapping("/appNew")
@Api(value = "newApp接口", description = "点击量模块的接口")
public class AppControllerHits {
	@Autowired
	private AppServiceNew appServicenew;
	/**
	 * 每晚 两点定时更新 点击量 迫于生计 不得不写 写完心里好痛啊
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void updateStudentpageView2(Graduated graduated) {
		appServicenew.updateStudentpageView2(graduated);

	}
}
