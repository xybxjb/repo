package cn.deepcoding.page;



public class Page {

	private int page;
	private int start;
	private int rows;
	public int getStart() {
		
		return (page-1)*rows;//表示第一页从第0条数据开始显示数据
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
