package qp.cps.service;

import static com.google.common.base.Objects.equal;

import org.springframework.stereotype.Service;
import qp.cps.response.model.common.PageModel;
//import qp.cps.util.ConversionUtil;
import qp.cps.util.DateUtil;
//import qp.cps.util.StringUtil;
import qp.cps.util.PaginationUtil;
import qp.cps.dto.request.LoginRequestDTO;
import qp.cps.dto.request.NewCustomerRequestDTO;
import qp.cps.dto.response.LoginResponseDTO;
import qp.cps.model.Customer;
import qp.cps.model.User;
import qp.cps.repository.AnncRepository;
import qp.cps.repository.UserRepository;
import qp.cps.repository.CustRepository;
import qp.cps.dto.publicsite.AnnouncementDto;
import qp.cps.dto.publicsite.BulletinSearchDto;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.joda.time.Period;
//import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.Optional;
import qp.cps.model.PublicAnnouncement;
import java.time.LocalDate;

@Service
@Transactional
public class AnnouncementService {

	@Autowired
	private AnncRepository anncRepository;


	/**
	 * Saves the specified customer application
	 * 
	 * @param customerApplication
	 */

	public PageModel<PublicAnnouncement> searchAnnouncements(BulletinSearchDto searchDto) {
		Integer pageSize = searchDto.getPageSize();
		Integer pageNo = Math.max(0,searchDto.getPage()-1);

		Pageable pagesetting = PaginationUtil.getPageable(pageNo, pageSize);

		//Page<PublicAnnouncement> pageResult = anncRepository.searchAnnouncements(LocalDate.now(), searchDto.publishDateFrom,searchDto.publishDateTo,pagesetting);
		Page<PublicAnnouncement> pageResult = anncRepository.searchAnnouncements(LocalDate.now(),pagesetting);

		List<PublicAnnouncement> resultModelList = pageResult.getContent();

		return new PageModel<PublicAnnouncement>(resultModelList, pageResult.getTotalElements(), pageResult.getNumber(),
				pageResult.getSize());
	}
	
	public PublicAnnouncement getBulletinView(Integer bulletinUnitId) {
		Optional<PublicAnnouncement> publicAnnc=anncRepository.findById(bulletinUnitId);
		return publicAnnc.get();
	}


}
