package cn.deepcoding.model;



/**
 * 考勤model类
 * @author 杨乐乐
 *
 */
public class Attence {
	private String id;//唯一ID
    private String userId;//用户ID 
    private String workDate;//工作日
    private Integer checkType;//考勤类型 0代表OnDuty：上班 1代表OffDuty：下班
    private String baseCheckTime;//应打卡时间
    private String userCheckTime;//实际打卡时间
    private Integer timeResult;//时间打卡结果0 Normal 正常 1 Early早退 2 Late迟到 3 SeriousLate严重迟到 4 Absenteeism逃课迟到 5NotSigned未打卡
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getBaseCheckTime() {
		return baseCheckTime;
	}
	public void setBaseCheckTime(String baseCheckTime) {
		this.baseCheckTime = baseCheckTime;
	}
	public String getUserCheckTime() {
		return userCheckTime;
	}
	public void setUserCheckTime(String userCheckTime) {
		this.userCheckTime = userCheckTime;
	}
	public Integer getCheckType() {
		return checkType;
	}
	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}
	public Integer getTimeResult() {
		return timeResult;
	}
	public void setTimeResult(Integer timeResult) {
		this.timeResult = timeResult;
	}
    
    
}
