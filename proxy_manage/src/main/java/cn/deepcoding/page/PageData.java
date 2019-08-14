package cn.deepcoding.page;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="分页对象",description="分页PageData")
public class PageData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="每页个数")
	private int total;
	@ApiModelProperty(value="当前页内容")
	private List<?> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}	
