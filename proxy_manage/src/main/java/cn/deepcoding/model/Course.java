package cn.deepcoding.model;


//课程
public class Course{
	private Integer id;
	private String name;
	private Major major; //专业
	private String inputName;
	

	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
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
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", major=" + major + ", inputName=" + inputName + "]";
	}
	
}
