package qp.cps.response.model;

public class ResponsePaginationModel<T> extends ResponseModel<T>{

	private long totalCount;
	
	private int currentPage;
	
	private int pageSize;

	public ResponsePaginationModel(T data, long totalCount, int currentPage, int size) {
		super(data);
		this.setTotalCount(totalCount);
		this.setCurrentPage(currentPage);
		this.setPageSize(size);
	}
	
	public ResponsePaginationModel(T data, int currentPage, int size, String message) {
		super(data);
		this.setTotalCount(0);
		this.setCurrentPage(currentPage+1);
		this.setPageSize(size);
		this.setMessage(message);
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage + 1;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
