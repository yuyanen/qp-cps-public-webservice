package qp.scs.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtil{
    
	private static int defaultPageIndex = 0;
	private static int defaultPageSize = 10;
	
	public PaginationUtil(){
		
	}
	
	public static Pageable getPageable(Integer targetPage, Integer pageSize){
		
		Pageable pageable = null;
		
		if(null!= targetPage && null!= pageSize){
			pageable = PageRequest.of(targetPage >= defaultPageIndex? targetPage:defaultPageIndex, pageSize >= defaultPageSize? pageSize:defaultPageSize);
		}else if(null!= pageSize){
			pageable = PageRequest.of(defaultPageIndex, pageSize);
		}
		
		return pageable;
		
	}
	
	public static Pageable getPageableWithSort(Integer targetPage, Integer pageSize, Sort sort){
		
		Pageable pageable = null;
		
		if(null!= targetPage && null!= pageSize){
			pageable = PageRequest.of(targetPage >= defaultPageIndex? targetPage:defaultPageIndex, pageSize >= defaultPageSize? pageSize:defaultPageSize, sort);
		}else if(null!= pageSize){
			pageable = PageRequest.of(defaultPageIndex, pageSize, sort);
		}
		
		return pageable;
		
	}
		
		
}
