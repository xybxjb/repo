package cn.deepcoding.model;

import java.io.Serializable;
/**
 * 推荐人pojo类
 * @author zhaoyihe
 * @date 2019年5月24日
 *
 */
public class Referrer implements Serializable {
	//主键id 
	private Integer id;
	//姓名
	private String name;
	//联系电话
	private String phone;
	//微信
	private String wechat;
	//机构名称
	private String organization;
	//推荐人姓名
	private String referrer_name;
	//推荐人电话
	private String referrer_tel;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getReferrer_name() {
		return referrer_name;
	}
	public void setReferrer_name(String referrer_name) {
		this.referrer_name = referrer_name;
	}
	public String getReferrer_tel() {
		return referrer_tel;
	}
	public void setReferrer_tel(String referrer_tel) {
		this.referrer_tel = referrer_tel;
	}
	
	public Referrer( String name, String phone, String wechat, String organization, String referrer_name,
			String referrer_tel) {
		super();
		
		this.name = name;
		this.phone = phone;
		this.wechat = wechat;
		this.organization = organization;
		this.referrer_name = referrer_name;
		this.referrer_tel = referrer_tel;
	}
	
	public Referrer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Referrer [id=" + id + ", name=" + name + ", phone=" + phone + ", wechat=" + wechat + ", organization="
				+ organization + ", referrer_name=" + referrer_name + ", referrer_tel=" + referrer_tel + "]";
	}
	
	
}
