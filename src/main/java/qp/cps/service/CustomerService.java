package qp.cps.service;

import static com.google.common.base.Objects.equal;

import org.springframework.stereotype.Service;
import qp.cps.response.model.common.PageModel;
//import qp.cps.util.ConversionUtil;
import qp.cps.util.DateUtil;
//import qp.cps.util.StringUtil;

import qp.cps.dto.request.LoginRequestDTO;
import qp.cps.dto.request.NewCustomerRequestDTO;
import qp.cps.dto.response.LoginResponseDTO;
import qp.cps.model.Customer;
import qp.cps.model.User;
import qp.cps.repository.CustomerRepository;
import qp.cps.repository.UserRepository;
import qp.cps.repository.CustRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.joda.time.Period;

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


@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustRepository custRepository;


	/**
	 * Saves the specified customer application
	 * 
	 * @param customerApplication
	 */
	public void registerCustomer(NewCustomerRequestDTO request) {
	
		Customer customer=new Customer();
		customer.contactPerson=request.contactPerson;
		customer.companyName=request.companyName;
		customer.phone=request.phone;
		customer.email =request.email;
		customer.id=request.id;
		customer.address=request.address;
		customer.buildingFloorUnit=request.buildingFloorUnit;
		customer.postcode=request.postcode;
		
		customerRepository.save(customer);
		
	}
	
	public PageModel<Customer> findCustomerList(String createDateFromStr, String createDateToStr, Pageable pagesetting) {
		List<String> errorMessages = new ArrayList<String>();
		Date requestDateFrom = null;
		Date requestDateTo = null;
		Date approveDateFrom = null;
		Date approveDateTo = null;
		if (createDateFromStr != null) {
			try {

				requestDateFrom = new SimpleDateFormat("dd-MM-yyyy").parse(createDateFromStr);

			} catch (ParseException e) {
				errorMessages.add("Create Date From");
			}
		}
		
		if (createDateToStr != null) {
			try {

				requestDateTo = new SimpleDateFormat("dd-MM-yyyy").parse(createDateToStr);

			} catch (ParseException e) {
				errorMessages.add("Create Date To");
			}
		}
		if (requestDateTo != null) {
			// add one day to the date, cause the sql does not include this date.
			// (reason: sql >= and <= result in the = value to not shown)
			Calendar c = Calendar.getInstance();
			c.setTime(requestDateTo);
			c.add(Calendar.DATE, 1);
			requestDateTo = c.getTime();
			
		}
			

		Page<Customer> pageResult = custRepository.findCustomerList(
				DateUtil.dateDdMmmYyyy(requestDateFrom), DateUtil.dateDdMmmYyyy(requestDateTo), pagesetting);

		List<Customer> resultModelList = pageResult.getContent();

		return new PageModel<Customer>(resultModelList, pageResult.getTotalElements(), pageResult.getNumber(),
				pageResult.getSize());
	}


	public List<Customer> getCustomers() {
		
		List<Customer> result = new ArrayList<Customer>();
		  customerRepository.findAll().forEach(result::add);
		  return result;
	}
	
	public Customer getCustomerById(String customerId) {
		
		Optional<Customer> custOpt = customerRepository.findById(customerId);	
		return custOpt.get();
	}

}
