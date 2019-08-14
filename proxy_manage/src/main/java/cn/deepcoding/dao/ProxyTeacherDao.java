package cn.deepcoding.dao;

import java.util.List;
import java.util.Map;

import cn.deepcoding.model.ProxyTeacherWatch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.ProxyTeacher;
import cn.deepcoding.model.ProxyTeacherPoint;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;

@Repository
public interface ProxyTeacherDao {
	public void add(ProxyTeacher proxyTeacher);

	public List<ProxyTeacher> getPageData(@Param("proxyTeacher") ProxyTeacher proxyTeacher, @Param("page") Page page);

	public List<ProxyTeacher> find();

	public Integer getTotal(@Param("proxyTeacher") ProxyTeacher proxyTeacher);

	public List<ProxyTeacher> getXj(Integer leaderid);

	public ProxyTeacher getParentProxyTeacherId(Integer id);

	public ProxyTeacher getParentId(Integer pid);

	public List<ProxyTeacher> getRankProxteacher(Integer rankId);

	public void delete(Integer id);

	public void deljia(ProxyTeacher proxyTeacher);

	public ProxyTeacher get(Integer id);

	public void update(ProxyTeacher proxyTeacher);

	public List<ProxyTeacher> getAll();

	public List<ProxyTeacher> FindByStudentId(Integer id);

	public List<ProxyTeacher> getById(Integer id);

	// 陈雪珍
	ProxyTeacher getOneProxyTeacher(@Param("studentId") Integer studentId);

	// 根据手机号获取招生老师信息
	Map<String, Object> getProxyTeacherByTel(String tel);

	ProxyTeacher getProxyTeacherByTel2(String appTel);

	// app 根据招生老师 id 查询上级 信息
	Integer getParentProxyTeacherId2(Integer id);

	ProxyTeacher getParentProxyTeacher(Integer id);

	// 根据招生老师 id 查询下级 信息
	List<ProxyTeacher> getSublevelProxyTeacher(Integer id);

	// 根据招生老师的id查询点位表中的数据
	public List<ProxyTeacherPoint> getpoint(Integer id);

	// 根据id删除变中数据
	public void deletepoint(@Param("id") Integer id, @Param("proxy_teacher_id") Integer proxy_teacher_id);

	// 添加点位表
	public void addpoint(ProxyTeacherPoint proxyteacherpoint);

	// 根据招生老师id和点位id查询点位数据
	public ProxyTeacherPoint selectpointbyid(@Param("id") Integer id, @Param("proxyTeacherId") int proxyTeacherId);

	// 根据招生老师id和点位表id修改点位数据
	public void updatepoint(ProxyTeacherPoint proxyteacherpoint);

	/**
	 * 根据招生老师的ID与学生入学时间获取当月招生点位 顺便吐槽这个点位表竟然在招生老师这个接口内
	 * 
	 * 哈哈哈
	 * 
	 */
	public Double getpointByStudentJointimeAndProxyTeacherId(Student student);

	// 查询出最新添加的日期
	public ProxyTeacherPoint selectponintbynew(@Param("proxyTeacherId") Integer proxyTeacherId);

	// 获取当前日期该招生老师得点位
	public ProxyTeacherPoint getPointByNow(Integer proxyTeacherId);

	// 根据添加得开始与结束日期判断是否重复
	public Double getpointByTimeAndproxyTeacherId(@Param("time") String time, @Param("proxyTeacherId") Integer proxyTeacherId, @Param("id")Integer id);

	/**
	 * 根据录入得开始于结束时间判断是否有包含信息
	 * 因为肯定不止一条所有返回数量
	 */
	public Integer getpointByProxyTeacherPoint(ProxyTeacherPoint proxyteacherpoint);

	//添加招生老师的微信信息
	void addProxyTeacherWatchInfo(@Param("proxyTeacherWatch")ProxyTeacherWatch proxyTeacherWatch);
	//绑定
	void boundWatch(@Param("proxyTeacherId")Integer proxyTeacherId,@Param("watchId")Integer watchId);
	//根据openid查询主键
	Integer selectIdByOpenID(@Param("openid")String openid);
}
