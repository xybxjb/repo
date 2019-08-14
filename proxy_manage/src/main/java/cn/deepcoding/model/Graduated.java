package cn.deepcoding.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 就业信息模型
 * */
public class Graduated {
	@ApiModelProperty(value="就业精英的学生 的id")
	private  Integer id;//自增ID
	@ApiModelProperty(value="就业学生")
	private Student student ;//学生信息
	@ApiModelProperty(value="就业信息标题")
	private  String  title;//标题
	@ApiModelProperty(hidden=true)
	private  String  createTime;//创建时间
	@ApiModelProperty(value="就业内容文档")
	private  String  content;//内容文档
	@ApiModelProperty(value="就业时间")
	private  String  graduatedTime;//毕业时间
	@ApiModelProperty(value="就业信息浏览量")
	private  Integer pageView;//浏览量
	@ApiModelProperty(hidden=true)
	private Integer elite;    //  1 为 精英 2为普通
	@ApiModelProperty(value="就业信息浏览量")
	private String img;       // 图片
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getGraduatedTime() {
		return graduatedTime;
	}
	public void setGraduatedTime(String graduatedTime) {
		this.graduatedTime = graduatedTime;
	}
	public Integer getPageView() {
		return pageView;
	}
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	public Integer getElite() {
		return elite;
	}
	public void setElite(Integer elite) {
		this.elite = elite;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Graduated [id=" + id + ", student=" + student + ", title=" + title + ", createTime=" + createTime
				+ ", content=" + content + ", graduatedTime=" + graduatedTime + ", pageView=" + pageView + ", elite="
				+ elite + ", img=" + img + "]";
	}
	
	
	
	
}
