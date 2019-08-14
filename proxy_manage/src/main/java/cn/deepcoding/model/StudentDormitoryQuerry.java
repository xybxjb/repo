package cn.deepcoding.model;

//展示宿舍详细信息
public class StudentDormitoryQuerry {
	private Integer id;
	private String name;
	private String time;
	private String starttime;
	private String adress;
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	

	public StudentDormitoryQuerry(Integer id, String name, String time, String starttime, String adress, String note) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.starttime = starttime;
		this.adress = adress;
		this.note = note;
	}

	@Override
	public String toString() {
		return "StudentDormitoryQuerry [id=" + id + ", name=" + name + ", time=" + time + ", starttime=" + starttime
				+ ", adress=" + adress + ", note=" + note + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public StudentDormitoryQuerry() {
		super();
		// TODO Auto-generated constructor stub
	}

}
