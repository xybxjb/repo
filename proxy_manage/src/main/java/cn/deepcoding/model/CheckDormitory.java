package cn.deepcoding.model;
/*
 * 宿舍查寝记录
 * @author 张旭
 * */
public class CheckDormitory {
	private Integer id;//ID
	private Integer did;//宿舍ID
	private Dormitory dormitory;//宿舍信息
	private String checkdata;//查寝时间
	private Integer actual;//实住人数
	private Integer absence;//缺勤人数
	private Integer situation;//宿舍状况
	private String note;//备注
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Dormitory getDormitory() {
		return dormitory;
	}
	public void setDormitory(Dormitory dormitory) {
		this.dormitory = dormitory;
	}
	public String getCheckdata() {
		return checkdata;
	}
	public void setCheckdata(String checkdata) {
		this.checkdata = checkdata;
	}
	public Integer getActual() {
		return actual;
	}
	public void setActual(Integer actual) {
		this.actual = actual;
	}
	public Integer getAbsence() {
		return absence;
	}
	public void setAbsence(Integer absence) {
		this.absence = absence;
	}
	public Integer getSituation() {
		return situation;
	}
	public void setSituation(Integer situation) {
		this.situation = situation;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "CheckDormitory [id=" + id + ", did=" + did + ", dormitory=" + dormitory + ", checkdata=" + checkdata
				+ ", actual=" + actual + ", absence=" + absence + ", situation=" + situation + ", note=" + note + "]";
	}
	

}
