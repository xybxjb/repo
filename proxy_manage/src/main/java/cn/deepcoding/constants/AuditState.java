package cn.deepcoding.constants;

import cn.deepcoding.util.CodeBaseEnum;

public enum AuditState implements CodeBaseEnum {
	
	on1(0,"未审核"),
	on2(1,"审核未通过"),
	on3(2,"待报销"),
	on4(3,"已报销");
	
	
	private String text;
	private Integer code;
	
	private AuditState(Integer code,String text ) {
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
	public AuditState setAuditState(Integer index){
		for(AuditState auditState : AuditState.values()) {  
            if(auditState.text == text) {  
            	System.out.println(text);
                return auditState;  
            }  
        }  
		return null;
	}
	public AuditState getAuditState(Integer index){
		for(AuditState auditState : AuditState.values()) {  
            if(auditState.text == text) {  
            	System.out.println(text);
                return auditState;  
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
