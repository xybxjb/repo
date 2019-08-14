package cn.deepcoding.model;

import java.io.Serializable;
/**
 * 就业人员pojo
 * @author zhaoyihe
 * @date 2019年6月1日
 *
 */
public class FindJodPreson implements Serializable {
	//学生id
	private Integer id;
	//学生名字
	private String name;
	//学生家庭地址
	private String address;
	//图片
	private String picture;
	//就业时间
	private String gradution_time;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getGradution_time() {
		return gradution_time;
	}
	public void setGradution_time(String gradution_time) {
		this.gradution_time = gradution_time;
	}
	@Override
	public String toString() {
		return "FindJodPreson [id=" + id + ", name=" + name + ", address=" + address + ", picture=" + picture
				+ ", gradution_time=" + gradution_time + "]";
	}
	
	
}
