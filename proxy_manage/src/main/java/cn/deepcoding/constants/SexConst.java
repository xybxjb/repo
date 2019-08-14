package cn.deepcoding.constants;


import cn.deepcoding.util.CodeBaseEnum;

public enum SexConst implements CodeBaseEnum {

	male(1,"男"),
	female(2,"女");
	
	private String text;
	private Integer code;
	
	private SexConst(Integer code,String text ) {
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

	public SexConst getSexConst(Integer index) {
		for(SexConst sexConst : SexConst.values()) {  
            if(sexConst.text == text) {  
            	System.out.println(text);
                return sexConst;  
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
