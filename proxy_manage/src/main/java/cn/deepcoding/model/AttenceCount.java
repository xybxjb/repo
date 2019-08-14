package cn.deepcoding.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="考勤信息对象",description="考勤信息对象 AttenceCount")
public class AttenceCount {
    private String studentName;//学生姓名
    private String userId;//钉钉ID
    @ApiModelProperty(value="正常打卡")
    private int normal;//正常打卡
    @ApiModelProperty(value="早退")
    private int early;//早退
    @ApiModelProperty(value="迟到")
    private int late;//迟到
    @ApiModelProperty(value="严重迟到")
    private int seriousLate;//严重迟到
    @ApiModelProperty(value="旷课")
    private int absenteeism;//矿工迟到
    @ApiModelProperty(value="未打卡")
    private int notSigned;//未打卡
    @ApiModelProperty(value="打卡日期")
    private String workDate;  // 打卡日期
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getNormal() {
		return normal;
	}
	public void setNormal(int normal) {
		this.normal = normal;
	}
	public int getEarly() {
		return early;
	}
	public void setEarly(int early) {
		this.early = early;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getSeriousLate() {
		return seriousLate;
	}
	public void setSeriousLate(int seriousLate) {
		this.seriousLate = seriousLate;
	}
	public int getAbsenteeism() {
		return absenteeism;
	}
	public void setAbsenteeism(int absenteeism) {
		this.absenteeism = absenteeism;
	}
	public int getNotSigned() {
		return notSigned;
	}
	public void setNotSigned(int notSigned) {
		this.notSigned = notSigned;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	   	
}
