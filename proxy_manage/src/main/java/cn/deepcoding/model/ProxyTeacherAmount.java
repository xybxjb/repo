package cn.deepcoding.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class ProxyTeacherAmount extends ProxyTeacher {
	
	private Double amount;//提成总金额
	private Double noPaid;//未提成总额
	private List<ProxyTeacherAmount> children =new LinkedList<>();
	
	
	public Double getNoPaid() {
		return noPaid;
	}

	public void setNoPaid(Double noPaid) {
		this.noPaid = noPaid;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public String getSex() {
		// TODO Auto-generated method stub
		return super.getSex();
	}

	@Override
	public void setSex(String sex) {
		// TODO Auto-generated method stub
		super.setSex(sex);
	}

	@Override
	public String getIdcard() {
		// TODO Auto-generated method stub
		return super.getIdcard();
	}

	@Override
	public void setIdcard(String idcard) {
		// TODO Auto-generated method stub
		super.setIdcard(idcard);
	}

	@Override
	public String getTel() {
		// TODO Auto-generated method stub
		return super.getTel();
	}

	@Override
	public void setTel(String tel) {
		// TODO Auto-generated method stub
		super.setTel(tel);
	}

	@Override
	public Integer getAge() {
		// TODO Auto-generated method stub
		return super.getAge();
	}

	@Override
	public void setAge(Integer age) {
		// TODO Auto-generated method stub
		super.setAge(age);
	}

	@Override
	public String getWechat() {
		// TODO Auto-generated method stub
		return super.getWechat();
	}

	@Override
	public void setWechat(String wechat) {
		// TODO Auto-generated method stub
		super.setWechat(wechat);
	}


	

	@Override
	public String getQq() {
		// TODO Auto-generated method stub
		return super.getQq();
	}

	@Override
	public void setQq(String qq) {
		// TODO Auto-generated method stub
		super.setQq(qq);
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return super .getStudents();
	}

	@Override
	public void setStudents(List<Student> students) {
		// TODO Auto-generated method stub
		super.setStudents(students);
	}

	
	

	
	@Override
	public List<VisitRecords> getVisitRecords() {
		// TODO Auto-generated method stub
		return super.getVisitRecords();
	}

	@Override
	public void setVisitRecords(List<VisitRecords> visitRecords) {
		// TODO Auto-generated method stub
		super.setVisitRecords(visitRecords);
	}

	/*@Override
	public Date getJoinDate() {
		// TODO Auto-generated method stub
		return super.getJoinDate();
	}

	@Override
	public void setJoinDate(Date joinDate) {
		// TODO Auto-generated method stub
		super.setJoinDate(joinDate);
	}*/

	@Override
	public void setPoint(Double point) {
		// TODO Auto-generated method stub
		super.setPoint(point);
	}

	

	@Override
	public Integer getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}

	@Override
	public void setState(Integer state) {
		// TODO Auto-generated method stub
		super.setState(state);
	}

	

	@Override
	public Double getPoint() {
		// TODO Auto-generated method stub
		return super.getPoint();
	}

	@Override
	public String getOpeningBank() {
		// TODO Auto-generated method stub
		return super.getOpeningBank();
	}

	@Override
	public void setOpeningBank(String openingBank) {
		// TODO Auto-generated method stub
		super.setOpeningBank(openingBank);
	}

	@Override
	public String getBankcard() {
		// TODO Auto-generated method stub
		return super.getBankcard();
	}

	@Override
	public void setBankcard(String bankcard) {
		// TODO Auto-generated method stub
		super.setBankcard(bankcard);
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return super.getAddress();
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		super.setAddress(address);
	}

	
	@Override
	public String getIntroducerName() {
		// TODO Auto-generated method stub
		return super.getIntroducerName();
	}

	@Override
	public void setIntroducerName(String introducerName) {
		// TODO Auto-generated method stub
		super.setIntroducerName(introducerName);
	}

	@Override
	public String getIntroducerType() {
		// TODO Auto-generated method stub
		return super.getIntroducerType();
	}

	@Override
	public void setIntroducerType(String introducerType) {
		// TODO Auto-generated method stub
		super.setIntroducerType(introducerType);
	}

	@Override
	public Rank getRanks() {
		// TODO Auto-generated method stub
		return super.getRanks();
	}

	@Override
	public void setRanks(Rank ranks) {
		// TODO Auto-generated method stub
		super.setRanks(ranks);
	}



	@Override
	public ProxyTeacher getParentProxyTeacherId() {
		// TODO Auto-generated method stub
		return super.getParentProxyTeacherId();
	}

	@Override
	public void setParentProxyTeacherId(ProxyTeacher parentProxyTeacherId) {
		// TODO Auto-generated method stub
		super.setParentProxyTeacherId(parentProxyTeacherId);
	}

	@Override
	public String getRemark() {
		// TODO Auto-generated method stub
		return super.getRemark();
	}

	@Override
	public void setRemark(String remark) {
		// TODO Auto-generated method stub
		super.setRemark(remark);
	}

	@Override
	public String getBankName() {
		// TODO Auto-generated method stub
		return super.getBankName();
	}

	@Override
	public void setBankName(String bankName) {
		// TODO Auto-generated method stub
		super.setBankName(bankName);
	}

	@Override
	public String getBankCardNumber() {
		// TODO Auto-generated method stub
		return super.getBankCardNumber();
	}

	@Override
	public void setBankCardNumber(String bankCardNumber) {
		// TODO Auto-generated method stub
		super.setBankCardNumber(bankCardNumber);
	}




	

	@Override
	public String getLeaderName() {
		// TODO Auto-generated method stub
		return super.getLeaderName();
	}

	@Override
	public void setLeaderName(String leaderName) {
		// TODO Auto-generated method stub
		super.setLeaderName(leaderName);
	}

	@Override
	public Date getJoinDate() {
		// TODO Auto-generated method stub
		return super.getJoinDate();
	}

	@Override
	public void setJoinDate(Date joinDate) {
		// TODO Auto-generated method stub
		super.setJoinDate(joinDate);
	}

	@Override
	public String getBegindate() {
		// TODO Auto-generated method stub
		return super.getBegindate();
	}

	@Override
	public void setBegindate(String begindate) {
		// TODO Auto-generated method stub
		super.setBegindate(begindate);
	}

	@Override
	public String getEnddate() {
		// TODO Auto-generated method stub
		return super.getEnddate();
	}

	@Override
	public void setEnddate(String enddate) {
		// TODO Auto-generated method stub
		super.setEnddate(enddate);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "ProxyTeacherAmount [amount=" + amount + "]";
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<ProxyTeacherAmount> getChildren() {
		return children;
	}

	public void setChildren(List<ProxyTeacherAmount> children) {
		this.children = children;
	}
	
}
