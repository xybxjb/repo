package cn.deepcoding.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.deepcoding.model.Attence;
import cn.deepcoding.model.AttenceGroup;
import cn.deepcoding.page.Page;

/**
 *考勤DAO
 * @author 杨乐乐
 *
 */
@Repository
public interface AttenceDao {
	//根据学生UserId查询学生考勤记录
      
    //添加考勤记录表
     int saveAttence(@Param("attences")List<Attence> attences);
     //查询学生个人考勤记录根据userId
     List<Attence> listByPagetStudentAttence(@Param("page")Page page,
             @Param("workDateFrom") String workDateFrom,@Param("workDateTo") String workDateTo,
             @Param("userId") String userId);
    //查询学生考勤记录总数
     int countStudentAttence(@Param("workDateFrom") String workDateFrom,@Param("workDateTo") String workDateTo,
     		@Param("userId") String userId); 
    //根据UserId查询考勤统计表
     List<AttenceGroup> getAttenceCount(@Param("workDateFrom") String workDateFrom,@Param("workDateTo") String workDateTo,@Param("userId") String userId);
     //获取表中最大时间
     String getMaxWorkDate();
     
     // 根据 钉钉id 查询最大日期
     String getMaxWorkDate1(String userId);
     // 根据 钉钉唯一标识符 查询最近一个月的考勤记录
     List<AttenceGroup> getAttenceCount1(@Param("userId") String userId);
     // 根据 钉钉唯一标识符 查询最近三个月的考勤记录
     List<AttenceGroup> getAttenceCount2(@Param("userId") String userId);
     // 根据 钉钉唯一标识符 查询最近六个月的考勤记录
     List<AttenceGroup> getAttenceCount3(@Param("userId") String userId);
     // 根据钉钉唯一标识符 查询最近一个月的 所有日期
     List<Attence> getAttenceWorkDate1(@Param("userId") String userId);
     // 根据钉钉唯一标识符 查询最近三个月的 所有日期
     List<Attence> getAttenceWorkDate2(@Param("userId") String userId);
     // 根据钉钉唯一标识符 查询最近六个月的 所有日期
     List<Attence> getAttenceWorkDate3(@Param("userId") String userId);
     // 根据钉钉唯一标识符 和日期  查询考勤记录
     List<AttenceGroup> getAttenceCountByWorkDate(@Param("userId") String userId,@Param("workDate") String workDate);


}
