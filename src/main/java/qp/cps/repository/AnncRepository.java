package qp.cps.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import qp.cps.model.PublicAnnouncement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnncRepository extends PagingAndSortingRepository<PublicAnnouncement, Integer> {


//	@Query(value = "SELECT b, m " + "FROM BalTransferBean b LEFT JOIN  PseaMasterBean m ON (b.pseaMasterBean = m) "
//			+ "WHERE m.uin = :uin and b.status = :status and b.approvOfficer = :loginId")
//	List<Object[]> findBalTransferByUinAndStatus(String uin, String status, String loginId);
//
//	@Query("SELECT c FROM PublicAnnouncement c " +
//	           "WHERE c.effectiveDate <= :today " +
//	           "AND c.expiryDate >= :today " +
//	           "AND c.effectiveDate >= :publishDateFrom " +
//	           "AND c.effectiveDate <= :publishDateTo")	
//	Page<PublicAnnouncement> searchAnnouncements(
//			@Param("today") LocalDate localDate ,@Param("publishDateFrom") LocalDate publishDateFrom,@Param("publishDateTo") LocalDate publishDateTo,
//			Pageable paging);
	
	@Query("SELECT c FROM PublicAnnouncement c " +
		       "WHERE c.effectiveDate < :today " +
		       "AND c.expiryDate > :today")
		Page<PublicAnnouncement> searchAnnouncements(
		        @Param("today") LocalDate today, Pageable paging);


	
//	@Query(value = "select count(b) from BalTransferBean b where b.status = 'P' and b.approvOfficer = :approvOfficer")
//	public Integer countPendingRecord(@Param("approvOfficer") String subjectId);
	
	}
