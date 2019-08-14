package cn.deepcoding.constants;

import cn.deepcoding.util.CodeBaseEnum;

public enum WageState implements CodeBaseEnum {
	
	ws1(0,"待结算"),
	ws2(1,"已结算");
	
	private Integer code;
	private String text;
	
	private WageState(Integer code, String text) {
		this.code = code;
		this.text = text;
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
	
	public WageState setWageState(Integer index){
		for(WageState wageState:WageState.values()){
			if(wageState.text == text){
				System.out.println(text);
				return wageState;
			}
		}
		return null;
	}
	public WageState getWageState(Integer index){
		for(WageState wageState:WageState.values()){
			if(wageState.text == text){
				System.out.println(text);
				return wageState;
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
