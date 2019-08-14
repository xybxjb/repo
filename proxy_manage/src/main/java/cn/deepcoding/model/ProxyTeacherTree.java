package cn.deepcoding.model;

import java.util.LinkedList;
import java.util.List;

public class ProxyTeacherTree {
	private Integer pid;
	private ProxyTeacher node;
	private List<ProxyTeacherTree> nodes = new LinkedList<ProxyTeacherTree>();
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public ProxyTeacher getNode() {
		return node;
	}
	public void setNode(ProxyTeacher node) {
		this.node = node;
	}
	public List<ProxyTeacherTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<ProxyTeacherTree> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
