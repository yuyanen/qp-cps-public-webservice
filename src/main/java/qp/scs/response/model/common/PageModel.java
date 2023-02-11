package qp.scs.response.model.common;

import java.util.List;

public class PageModel<K> {

	private List<K> result;
	
	private long totalCount;
	
	private int currentPage;
	
	private int pageSize;

	public PageModel(){
		
	}
			
	public PageModel(List<K> result, long totalCount, int currentPage, int pageSize){
		this.result = result;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public List<K> getResult() {
		return result;
	}

	public void setResult(List<K> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
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
