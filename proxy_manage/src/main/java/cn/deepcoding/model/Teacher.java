package cn.deepcoding.model;

import java.io.Serializable;

import cn.deepcoding.constants.TeacherState;

public class Teacher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2732828132366288439L;
	private Integer id;
	private String name;
	
	//private Integer state; //状态   2=离职 ，1=在职
	private TeacherState state;
	
	public TeacherState getState() {
		return state;
	}
	public String getStateText() {
		return state.getText();
	}

	public void setState(TeacherState state) {
		this.state = state;
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

	/*public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
*/

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", state=" + state + "]";
	}

	


}
