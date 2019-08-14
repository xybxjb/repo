package cn.deepcoding.model;

import java.sql.Date;
import java.util.List;

import cn.deepcoding.constants.ProxyTeacherSource;
import io.swagger.annotations.ApiModelProperty;

//招生老师
public class ProxyTeacher {
	@ApiModelProperty(value = "招生老师id编号")
	private Integer id;
	@ApiModelProperty(value = "招生老师姓名")
	private String name;
	@ApiModelProperty(value = "招生老师性别")
	private String sex;
	@ApiModelProperty(hidden = true)
	private String idcard;
	@ApiModelProperty(value = "招生老师联系电话")
	private String tel;
	@ApiModelProperty(hidden = true)
	private Integer age;
	@ApiModelProperty(hidden = true)
	private String wechat;// 微信
	@ApiModelProperty(hidden = true)
	private String qq;
	@ApiModelProperty(hidden = true)
	private String bankcard;// 银行卡图片
	@ApiModelProperty(value = "招生老师地区")
	private String address;// 地址
	@ApiModelProperty(hidden = true)
	private Date joinDate; // 加入时间
	@ApiModelProperty(hidden = true)
	private String introducerName;// 介绍人
	@ApiModelProperty(hidden = true)
	private String introducerType;// 介绍人是否招生老师
	@ApiModelProperty(hidden = true)
	private Rank ranks;// 级别
	@ApiModelProperty(value = "招生老师点位")
	private Double point;// 点位
	@ApiModelProperty(hidden = true)
	private ProxyTeacher parentProxyTeacherId;// 上级人Id
	@ApiModelProperty(hidden = true)
	private String remark;// 备注
	@ApiModelProperty(hidden = true)
	private String bankName;// 银行卡姓名
	@ApiModelProperty(hidden = true)
	private String bankCardNumber;// 银行卡卡号
	@ApiModelProperty(hidden = true)
	private String openingBank;// 开户行
	@ApiModelProperty(hidden = true)
	private Integer state;
	@ApiModelProperty(hidden = true)
	private String begindate = null;
	@ApiModelProperty(hidden = true)
	private String enddate = null;
	@ApiModelProperty(hidden = true)
	private String leaderName;

	@ApiModelProperty(hidden = true)
	private List<VisitRecords> visitRecords;
	@ApiModelProperty(value = "招生老师学生数量")
	private Integer studentCount;
	@ApiModelProperty(value = "招生老师头像")
	private String headPortrait; // 招生老师头像
	/**
	 * 
	 * 点位改为从点位表动态获取
	 */
	private ProxyTeacherPoint proxyTeacherPoint;
	
	private ProxyTeacherSource source; //招生老师的数据来源
	public ProxyTeacherPoint getProxyTeacherPoint() {
		return proxyTeacherPoint;
	}

	public void setProxyTeacherPoint(ProxyTeacherPoint proxyTeacherPoint) {
		this.proxyTeacherPoint = proxyTeacherPoint;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public List<VisitRecords> getVisitRecords() {
		return visitRecords;
	}

	public void setVisitRecords(List<VisitRecords> visitRecords) {
		this.visitRecords = visitRecords;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ApiModelProperty(hidden = true)
	private List<Student> students;// 招生老师和学生是一对多

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getIntroducerName() {
		return introducerName;
	}

	public void setIntroducerName(String introducerName) {
		this.introducerName = introducerName;
	}

	public String getIntroducerType() {
		return introducerType;
	}

	public void setIntroducerType(String introducerType) {
		this.introducerType = introducerType;
	}

	public Rank getRanks() {
		return ranks;
	}

	public void setRanks(Rank ranks) {
		this.ranks = ranks;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public ProxyTeacher getParentProxyTeacherId() {
		return parentProxyTeacherId;
	}

	public void setParentProxyTeacherId(ProxyTeacher parentProxyTeacherId) {
		this.parentProxyTeacherId = parentProxyTeacherId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}


	public ProxyTeacherSource getSource() {
		return source;
	}
	public String getSourceText() {
		return source.getText();
	}

	public void setSource(ProxyTeacherSource source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "ProxyTeacher [id=" + id + ", name=" + name + ", sex=" + sex + ", idcard=" + idcard + ", tel=" + tel
				+ ", age=" + age + ", wechat=" + wechat + ", qq=" + qq + ", bankcard=" + bankcard + ", address="
				+ address + ", joinDate=" + joinDate + ", introducerName=" + introducerName + ", introducerType="
				+ introducerType + ", ranks=" + ranks + ", point=" + point + ", parentProxyTeacherId="
				+ parentProxyTeacherId + ", remark=" + remark + ", bankName=" + bankName + ", bankCardNumber="
				+ bankCardNumber + ", openingBank=" + openingBank + ", state=" + state + ", begindate=" + begindate
				+ ", enddate=" + enddate + ", leaderName=" + leaderName + ", visitRecords=" + visitRecords
				+ ", studentCount=" + studentCount + ", headPortrait=" + headPortrait + ", proxyTeacherPoint="
				+ proxyTeacherPoint + ", source=" + source + ", students=" + students + "]";
	}

}
