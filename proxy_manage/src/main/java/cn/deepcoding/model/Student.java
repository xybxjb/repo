package cn.deepcoding.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import cn.deepcoding.constants.FindJob;
import cn.deepcoding.constants.SexConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="学生对象",description="学生对象 Student")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id编号")
	private Integer id;
	@ApiModelProperty(value="学生姓名")
	private String name;
	@ApiModelProperty(hidden=true)
	private SexConst sex;
	@ApiModelProperty(hidden=true)
	private Integer age;
	@ApiModelProperty(hidden=true)
	private String idCard; // 身份证
	@ApiModelProperty(hidden=true)
	private String birthday; // 出生日期
	@ApiModelProperty(value="学生电话")
	private String tel; // 电话
	@ApiModelProperty(value="学生籍贯")
	private String address;// 地址
	@ApiModelProperty(value="学生入学时间")
	private String joinTime; // 入学时间
	@ApiModelProperty(hidden=true)
	private String education; // 学历
	@ApiModelProperty(hidden=true)
	private String degree; // 学位
	@ApiModelProperty(hidden=true)
	private String originalMajor; // 原专业
	@ApiModelProperty(hidden=true)
	private Integer number; // 毕业编号
	@ApiModelProperty(value="学生学习时长")
	private Integer studyTime; // 学习时长
	@ApiModelProperty(value="学生性别")
	private String sexText;
	@ApiModelProperty(value="学生专业")
	private Major major; // 专业
	@ApiModelProperty(value="学生学费")
	private double tuition; // 学费
	@ApiModelProperty(hidden=true)
	private double quarterage;// 住宿费
	@ApiModelProperty(hidden=true)
	private double incidentals;// 杂费
	@ApiModelProperty(hidden=true)
	private double unpaiTuition; // 未交学费
	@ApiModelProperty(hidden=true)
	private Classroom classroom; // 班级
	@ApiModelProperty(hidden=true)
	private Teacher teacher; // 老师
	@ApiModelProperty(hidden=true)
	private AdvisoryTeacher advisoryTeacher; // 咨询老师
	@ApiModelProperty(value="学生的招生老师")
	private ProxyTeacher proxyTeacher; // 招生老师
	@ApiModelProperty(hidden=true)
	private String relation; // 关系
	@ApiModelProperty(hidden=true)
	private String idCardPic; // 身份证图片
	@ApiModelProperty(hidden=true)
	private String idCardPic2; // 身份证图片
	@ApiModelProperty(hidden=true)
	private Integer simulationClassroom; // 模拟班级
	@ApiModelProperty(hidden=true)
	private String beforeAddress; // 出发地
	@ApiModelProperty(hidden=true)
	private String transportation; // 出行方式
	@ApiModelProperty(hidden=true)
	private String dingId;// 钉钉userID
	/*张旭
	 * 2018/12/15添加
	 * 住宿情况
	 * 默认为1
	 */
	@ApiModelProperty(hidden=true)
	private Integer dormitorynote;// 
	/*张旭
	 * 2019/2/25添加
	 * 提成情况
	 * 默认为：1——提成
	 *         0——不提成
	 */
	@ApiModelProperty(hidden=true)
	private Integer commission;
	/*
	 * 
	 */
	@ApiModelProperty(hidden=true)
	private String tuitionContinue;
	@ApiModelProperty(hidden=true)
	private String incidentalsContinue;

	private Date initialResideDate;//开始住宿时间；
	private  List<EmergencyPerson> persons;//家长信息，可多个
	
	
	private String remarks;
	
  

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setFindJobText(String findJobText) {
		this.findJobText = findJobText;
	}

	public List<EmergencyPerson> getPersons() {
		return persons;
	}

	public void setPersons(List<EmergencyPerson> persons) {
		this.persons = persons;
	}

	public Date getInitialResideDate() {
		return initialResideDate;
	}

	public void setInitialResideDate(Date initialResideDate) {
		this.initialResideDate = initialResideDate;
	}

	@ApiModelProperty(hidden=true)
	private String quarterageContinue;//住宿费到期时间；
	@ApiModelProperty(hidden=true)
	private List<Score> scores;
	@ApiModelProperty(value="学生已交老学费")
	private double amountTuition;   // 已交学费
	@ApiModelProperty(hidden=true)
	private FindJob findJob;
	@ApiModelProperty(value="学生是否就业")
	private String findJobText;
	@ApiModelProperty(hidden=true)
	private String picture;//学生照片
	public Integer getCommission() {
		return commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public Integer getDormitorynote() {
		return dormitorynote;
	}

	public void setDormitorynote(Integer dormitorynote) {
		this.dormitorynote = dormitorynote;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public String getIdCardPic2() {
		return idCardPic2;
	}

	public void setIdCardPic2(String idCardPic2) {
		this.idCardPic2 = idCardPic2;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public String getQuarterageContinue() {
		return quarterageContinue;
	}

	public void setQuarterageContinue(String quarterageContinue) {
		this.quarterageContinue = quarterageContinue;
	}

	private Date entDate;

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getIncidentalsContinue() {
		return incidentalsContinue;
	}

	public void setIncidentalsContinue(String incidentalsContinue) {
		this.incidentalsContinue = incidentalsContinue;
	}

	public String getTuitionContinue() {
		return tuitionContinue;
	}

	public void setTuitionContinue(String tuitionContinue) {
		this.tuitionContinue = tuitionContinue;
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



	public SexConst getSex() {
		return sex;
	}

	public void setSex(SexConst sex) {
		this.sex = sex;
	}

	public String getSexText() {
		return sex.getText();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getOriginalMajor() {
		return originalMajor;
	}

	public void setOriginalMajor(String originalMajor) {
		this.originalMajor = originalMajor;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public double getTuition() {
		return tuition;
	}

	public void setTuition(double tuition) {
		this.tuition = tuition;
	}

	public double getQuarterage() {
		return quarterage;
	}

	public void setQuarterage(double quarterage) {
		this.quarterage = quarterage;
	}

	public double getIncidentals() {
		return incidentals;
	}

	public void setIncidentals(double incidentals) {
		this.incidentals = incidentals;
	}

	public double getUnpaiTuition() {
		return unpaiTuition;
	}

	public void setUnpaiTuition(double unpaiTuition) {
		this.unpaiTuition = unpaiTuition;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public AdvisoryTeacher getAdvisoryTeacher() {
		return advisoryTeacher;
	}

	public void setAdvisoryTeacher(AdvisoryTeacher advisoryTeacher) {
		this.advisoryTeacher = advisoryTeacher;
	}

	public ProxyTeacher getProxyTeacher() {
		return proxyTeacher;
	}

	public void setProxyTeacher(ProxyTeacher proxyTeacher) {
		this.proxyTeacher = proxyTeacher;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getIdCardPic() {
		return idCardPic;
	}

	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

	public Integer getSimulationClassroom() {
		return simulationClassroom;
	}

	public void setSimulationClassroom(Integer simulationClassroom) {
		this.simulationClassroom = simulationClassroom;
	}

	public String getBeforeAddress() {
		return beforeAddress;
	}

	public void setBeforeAddress(String beforeAddress) {
		this.beforeAddress = beforeAddress;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public void setSexText(String sexText) {
		this.sexText = sexText;
	}

	public String getDingId() {
		return dingId;
	}

	public void setDingId(String dingId) {
		this.dingId = dingId;
	}




	public double getAmountTuition() {
		return amountTuition;
	}

	public void setAmountTuition(double amountTuition) {
		this.amountTuition = amountTuition;
	}

	public FindJob getFindJob() {
		return findJob;
	}

	public void setFindJob(FindJob findJob) {
		this.findJob = findJob;
	}

	public String getFindJobText() {
		return findJob.getText();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", idCard=" + idCard
				+ ", birthday=" + birthday + ", tel=" + tel + ", address=" + address + ", joinTime=" + joinTime
				+ ", education=" + education + ", degree=" + degree + ", originalMajor=" + originalMajor + ", number="
				+ number + ", studyTime=" + studyTime + ", sexText=" + sexText + ", major=" + major + ", tuition="
				+ tuition + ", quarterage=" + quarterage + ", incidentals=" + incidentals + ", unpaiTuition="
				+ unpaiTuition + ", classroom=" + classroom + ", teacher=" + teacher + ", advisoryTeacher="
				+ advisoryTeacher + ", proxyTeacher=" + proxyTeacher + ", relation=" + relation + ", idCardPic="
				+ idCardPic + ", idCardPic2=" + idCardPic2 + ", simulationClassroom=" + simulationClassroom
				+ ", beforeAddress=" + beforeAddress + ", transportation=" + transportation + ", dingId=" + dingId
				+ ", dormitorynote=" + dormitorynote + ", commission=" + commission + ", tuitionContinue="
				+ tuitionContinue + ", incidentalsContinue=" + incidentalsContinue + ", initialResideDate="
				+ initialResideDate + ", persons=" + persons + ", remarks=" + remarks + ", quarterageContinue="
				+ quarterageContinue + ", scores=" + scores + ", amountTuition=" + amountTuition + ", findJob="
				+ findJob + ", findJobText=" + findJobText + ", picture=" + picture + ", entDate=" + entDate + "]";
	}

	
	

	

	
	
	
	

}
