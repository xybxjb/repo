package cn.deepcoding.constants;

import cn.deepcoding.util.CodeBaseEnum;

public enum ProxyTeacherSource implements CodeBaseEnum{
	
	back(1,"后台"),
	app(2,"app"),
	other(9,"其它");
	
	private String text;
	private Integer code;
	
	private ProxyTeacherSource(Integer code,String text ) {
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
	public ProxyTeacherSource setProxyTeacherSource(Integer index){
		for(ProxyTeacherSource proxyTeacherSource : ProxyTeacherSource.values()) {  
            if(proxyTeacherSource.text == text) {  
            	System.out.println(text);
                return proxyTeacherSource;  
            }  
        }  
		return null;
	}
	public ProxyTeacherSource getProxyTeacherSource(Integer index){
		for(ProxyTeacherSource proxyTeacherSource : ProxyTeacherSource.values()) {  
            if(proxyTeacherSource.text == text) {  
            	System.out.println(text);
                return proxyTeacherSource;  
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
