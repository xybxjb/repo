package cn.deepcoding.model;

import java.io.Serializable;
import java.util.List;

/**
 * 就业信息的分页model
 * @author root
 *
 */
public class GraduatedResult implements Serializable{

	//总记录数
	private long recordCount;
	//总页数
	private int totalPages;
	private List<Graduated> graduateds;
	
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<Graduated> getGraduateds() {
		return graduateds;
	}
	public void setGraduateds(List<Graduated> graduateds) {
		this.graduateds = graduateds;
	}
	
}
