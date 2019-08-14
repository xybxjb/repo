package cn.deepcoding.dao;

import java.util.List;

import org.springframework.stereotype.Component;


import cn.deepcoding.model.Area;
@Component
public interface AreaDao {

	List<Area> getAll();

}
/*
 * @Component注解
 * 	把普通pojo类实例化到spring容器中，相当于配置文件中的
 * 	<bean id="" class=""/>
 * 
 * */

