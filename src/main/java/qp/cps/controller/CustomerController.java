package qp.cps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qp.cps.response.model.ErrorResponseModel;
import qp.cps.response.model.ResponseModel;
import qp.cps.response.model.ResponsePaginationModel;
import qp.cps.response.model.common.PageModel;
import qp.cps.util.PaginationUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import qp.cps.dto.request.LoginRequestDTO;
import qp.cps.dto.request.NewCustomerRequestDTO;
import qp.cps.dto.request.RequestDTO;
import qp.cps.dto.response.LoginResponseDTO;
import qp.cps.dto.response.ProfileDetailResponseDTO;
import qp.cps.model.User;
import qp.cps.service.CustomerService;
import qp.cps.service.UserService;
import qp.cps.model.Customer;
@RestController
@RequestMapping(path = "/api/protected/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	@RequestMapping(path = "/exportFile", method= RequestMethod.POST)
//	  public ProfileDetailResponseDTO login(@Validated @RequestBody RequestDTO request) {
//		User user = userService.getUserFromToken(request.token);
//			
//		// [ Quick Profile query ] --------------------
//		ProfileDetailResponseDTO response = new ProfileDetailResponseDTO(user.username);
//		return response;
//		
//	  }
	
	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping(path = "/newCustomer", method = RequestMethod.POST)
	public void registerCustomer(@Validated @RequestBody NewCustomerRequestDTO request) {
		customerService.registerCustomer(request);
	}
	
	@RequestMapping(path = "/getAllCustomers", method = RequestMethod.GET)
	public List<Customer> getCustomers( ) {
		return customerService.getCustomers();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "findCustomerList")
	public ResponseModel<Object> findCustomerList(
			@RequestParam(required = false) String createDateFrom, @RequestParam(required = false) String createDateTo,
			@RequestParam(required = true, defaultValue = "0") Integer pageNo) {

		List<String> errorMessages = new ArrayList<String>();

		try {

			Integer pageSize = 10;
			Pageable pageable = PaginationUtil.getPageable(pageNo, pageSize);
			PageModel<Customer> resultModel = customerService.findCustomerList(createDateFrom,
					createDateTo, pageable);

			return new ResponsePaginationModel<Object>(resultModel.getResult(), resultModel.getTotalCount(),
					resultModel.getCurrentPage(), resultModel.getPageSize());

		} catch (Exception e) {
			// unexpected exception
			errorMessages.add("unexpected error");
			logger.error("error occured at customer-findCustomerList" + e);
		}

		return new ErrorResponseModel<Object>(errorMessages);

	}

}
