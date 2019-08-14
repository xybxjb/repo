package cn.deepcoding.model;

import java.io.Serializable;

public class AppIndexCycleImage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;// 图片id
	private String imageName;// 图片名称
	private String imageSrc;// 图片地址
	private Integer imageUse;// 是否使用当前图片作为轮播图；yes:1 / no:0
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public Integer getImageUse() {
		return imageUse;
	}
	public void setImageUse(Integer imageUse) {
		this.imageUse = imageUse;
	}
	
	@Override
	public String toString() {
		return "AppIndexCycleImage [id=" + id + ", imageName=" + imageName + ", imageSrc=" + imageSrc + ", imageUse="
				+ imageUse + "]";
	}
	
}
