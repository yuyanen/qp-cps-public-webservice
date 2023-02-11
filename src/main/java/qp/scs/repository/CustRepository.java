package qp.scs.repository;

import qp.scs.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CustRepository extends PagingAndSortingRepository<Customer, Long> {


//	@Query(value = "SELECT b, m " + "FROM BalTransferBean b LEFT JOIN  PseaMasterBean m ON (b.pseaMasterBean = m) "
//			+ "WHERE m.uin = :uin and b.status = :status and b.approvOfficer = :loginId")
//	List<Object[]> findBalTransferByUinAndStatus(String uin, String status, String loginId);

	@Query(value = "SELECT c " + "FROM Customer c")
	Page<Customer> findCustomerList(
			@Param("requestDateFrom") String requestDateFrom, @Param("requestDateTo") String requestDateTo,
			Pageable paging);

	
//	@Query(value = "select count(b) from BalTransferBean b where b.status = 'P' and b.approvOfficer = :approvOfficer")
//	public Integer countPendingRecord(@Param("approvOfficer") String subjectId);
	
	}
