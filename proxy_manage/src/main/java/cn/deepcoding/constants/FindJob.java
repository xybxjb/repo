package cn.deepcoding.constants;

import cn.deepcoding.util.CodeBaseEnum;

public enum FindJob implements CodeBaseEnum{
	
	on1(1,"在读"),
	on2(2,"就业"),
	on3(3,"离校");
	
	private String text;
	private Integer code;
	
	private FindJob(Integer code,String text ) {
		this.text = text;
		this.code = code;
	}
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
	public FindJob setFindJob(Integer index){
		for(FindJob findJob : FindJob.values()) {  
            if(findJob.text == text) {  
            	System.out.println(text);
                return findJob;  
            }  
        }  
		return null;
	}
	public FindJob getFindJob(Integer index){
		for(FindJob findJob : FindJob.values()) {  
            if(findJob.text == text) {  
            	System.out.println(text);
                return findJob;  
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
