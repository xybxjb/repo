package cn.deepcoding.constants;


import cn.deepcoding.util.CodeBaseEnum;

public enum TeacherState implements CodeBaseEnum {

	on(1,"在职"),
	off(2,"离职");
	
	private String text;
	private Integer code;
	
	private TeacherState(Integer code,String text ) {
		this.text = text;
		this.code = code;
	}
	//@JsonValue
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public TeacherState getTeacherState(Integer index) {
		for(TeacherState teacherState : TeacherState.values()) {  
            if(teacherState.text == text) {  
            	System.out.println(text);
                return teacherState;  
            }  
        }  
		return null;
	}
	public TeacherState setTeacherState(Integer index) {
		for(TeacherState teacherState : TeacherState.values()) {  
			if(teacherState.text == text) {  
				System.out.println(text);
				return teacherState;  
			}  
		}  
		return null;
	}

	@Override
	public int code() {
		// TODO Auto-generated method stub
		return code;
	}
	
}
