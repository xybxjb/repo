package cn.deepcoding.controller;


import java.util.ArrayList;
import java.util.List;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.deepcoding.model.Permission;
import cn.deepcoding.service.PermissionService;
import cn.deepcoding.util.TreeNoteUtil;
 
@Controller
@RequestMapping("permissions")
public class PermissionController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
    
    
    @Autowired
    private PermissionService permissionService;
    //查找所有的权限
    @RequestMapping(value = "selectPermission")
    @ResponseBody
    public Object selectPermission(HttpServletRequest request, HttpServletResponse response){
        //用于接收数据库查询到的数据
        List<Permission> list = new ArrayList<Permission>();
        //用于把接收到的数据改造成EasyUI tree认识的数据格式
        List<Permission> permission = new ArrayList<Permission>();
        try {
            //后台接收到的数据
            list = permissionService.selectPermission(new Permission());
            //TreeNoteUtil为工具类，用于改造数据
            permission = TreeNoteUtil.getFatherNode(list);
        } catch (Exception e) {
            LOGGER.error("PermissionController.selectPermission() error:" + e);
        }
        return permission;
        
    }
 // 去权限管理页面
 	@RequiresPermissions("permissions:index")
 	@RequestMapping("/index")
 	public String index(){
 		return "permission/permissions";
 	}
 	
 	// 查询所有
 	@RequestMapping("/getAll")
 	@ResponseBody
 	public List<Permission> getAll(Permission permissions){
 		return permissionService.getAll(permissions);
 	}
 	// 根据 id查询
 	@RequestMapping("/getById")
 	@ResponseBody
 	public Permission getById(Integer id){
 		return permissionService.getById(id);
 	}
 	// 修改
 	@RequestMapping("/update")
 	@ResponseBody
 	public void update(Permission permissions){
 		permissionService.update(permissions);
 	}
 	// 添加
 	@RequestMapping("/addPermission")
 	@ResponseBody
 	public void addPermission(Permission permissions){
 		permissionService.addPermission(permissions);
 	}
 	// 删除
 	@RequestMapping("/del")
 	@ResponseBody
 	public void del(Integer id){
 		permissionService.del(id);
 	}
 	//查询所有父级权限
 	@RequestMapping("/getPid")
 	@ResponseBody
 	public List<Permission> getPid(){
 		List<Permission> list = permissionService.getPid();
 		return list;
 	}
 	//通过cid查询父级权限
 	@RequestMapping("/getByCid")
 	@ResponseBody
 	public List<Permission> getByCid(Permission permission){
 		List<Permission> p = permissionService.getByCid(permission);
 		return p;
 	}
}
