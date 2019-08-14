package cn.deepcoding.model;

import java.io.Serializable;

public class Area implements Serializable {

	/*
	 * Serializable接口：实现序列化
	 * 	1.serialVersionUID：序列化ID
	 * 	2.序列化ID在eclipse中提供了两种生成策略
	 * 		2.1.一个是固定的 1L
	 * 		2.2.一个是随机生成一个不重复的 long 类型数据（实际上是使用 JDK 工具，根据类名、
	 * 			接口名、成员方法及属性等来生成）
	 * 		2.3.如果是通过网络传输的话，如果类中的serialVersionUID不一致，那么反序列化就不能正常进行。
	 * 		例如在客户端A中Person类的serialVersionUID=1L，而在客户端B中Person类的serialVersionUID=2L 
	 * 			那么就不能重构这个Person对象。
	 *	3.当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口。
	 * 
	 * */

	private static final long serialVersionUID = 4698819039939933664L;
	private String name;
	private String code;
	private String perentCode;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPerentCode() {
		return perentCode;
	}
	public void setPerentCode(String perentCode) {
		this.perentCode = perentCode;
	}



	@Override
	public String toString() {
		return "Area [name=" + name + ", code=" + code + ", perentCode=" + perentCode + "]";
	}

}
