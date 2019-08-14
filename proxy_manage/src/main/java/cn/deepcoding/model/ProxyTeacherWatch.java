package cn.deepcoding.model;

import cn.deepcoding.constants.SexConst;

/**
 * 招生老师微信信息
 */
public class ProxyTeacherWatch {
	private Integer id;// 主键ID
	private String openid;// 用户的唯一表示openid
	private String nickname;// 用户昵称
	private Integer sex;// 用户的性别
	private String city;// 用户的城市

	private String country;// 用户的国家
	private String province;// 用户的省份
	private ProxyTeacherIdAndWatchId proxyteacheridandwatchid;//关系对应
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public ProxyTeacherIdAndWatchId getProxyteacheridandwatchid() {
		return proxyteacheridandwatchid;
	}
	public void setProxyteacheridandwatchid(ProxyTeacherIdAndWatchId proxyteacheridandwatchid) {
		this.proxyteacheridandwatchid = proxyteacheridandwatchid;
	}
	
	public ProxyTeacherWatch(String openid, String nickname, Integer sex, String city, String country,
			String province) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.province = province;
	}
	
	public ProxyTeacherWatch() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProxyTeacherWatch [id=" + id + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", city=" + city + ", country=" + country + ", province=" + province + ", proxyteacheridandwatchid="
				+ proxyteacheridandwatchid + "]";
	}

	
	
}
