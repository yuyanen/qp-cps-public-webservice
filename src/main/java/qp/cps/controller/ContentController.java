package qp.cps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import qp.cps.dto.request.LoginRequestDTO;
import qp.cps.dto.request.RequestDTO;
import qp.cps.dto.response.LoginResponseDTO;
import qp.cps.dto.response.ProfileDetailResponseDTO;
import qp.cps.model.Customer;
import qp.cps.model.User;
import qp.cps.service.CustomerService;
import qp.cps.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class ContentController {
	
	@Autowired
	private UserService userService;
	
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
	
	  @RequestMapping("/exportFile")
	  public void exportFile(HttpServletResponse response,@RequestParam Map<String,String> requestParams)  throws IOException {
		 String customerId=requestParams.get("customerId");
		 String docType=requestParams.get("docType");

		 Customer customer=	customerService.getCustomerById(customerId);

		
		if(docType.equals("0"))	
		{
			
			Resource resource = new ClassPathResource("template\\Quotation.xlsx");

			FileInputStream file = new FileInputStream(resource.getFile());

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// XSSFWorkbook workbook = new XSSFWorkbook();
			// XSSFSheet sheet = workbook.createSheet("Drivers");

			XSSFSheet sheet = workbook.getSheet("Quote");

			sheet.getRow(6).getCell(5).setCellType(CellType.STRING);
			sheet.getRow(6).getCell(5).setCellValue(customer.id);
			
			sheet.getRow(9).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(9).getCell(0).setCellValue(customer.companyName);
			
			sheet.getRow(10).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(10).getCell(0).setCellValue(customer.buildingFloorUnit+", "+ customer.address);
			
			sheet.getRow(11).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(11).getCell(0).setCellValue("Singapore "+customer.postcode);

			sheet.getRow(12).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(12).getCell(0).setCellValue("Attn Person: "+ customer.contactPerson.toString());
			
			sheet.getRow(13).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(13).getCell(0).setCellValue("Phone: "+customer.phone.toString());
			
			userService.export(workbook, response, "application/vnd.ms-excel");
		}
		else if (docType.equals("1"))
		{
			Resource resource = new ClassPathResource("\\template\\Invoice.xlsx");

			FileInputStream file = new FileInputStream(resource.getFile());

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// XSSFWorkbook workbook = new XSSFWorkbook();
			// XSSFSheet sheet = workbook.createSheet("Drivers");

			XSSFSheet sheet = workbook.getSheet("Invoice");

			sheet.getRow(4).getCell(5).setCellType(CellType.STRING);
			sheet.getRow(4).getCell(5).setCellValue(customer.id);
			
			sheet.getRow(9).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(9).getCell(0).setCellValue(customer.companyName);
			
			sheet.getRow(10).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(10).getCell(0).setCellValue(customer.buildingFloorUnit+", "+ customer.address);
			
			sheet.getRow(11).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(11).getCell(0).setCellValue("Singapore "+customer.postcode);

			sheet.getRow(12).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(12).getCell(0).setCellValue("Attn Person: "+ customer.contactPerson.toString());
			
			sheet.getRow(13).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(13).getCell(0).setCellValue("Phone: "+customer.phone.toString());
			
			userService.export(workbook, response, "application/vnd.ms-excel");
		}
		else {
			Resource resource = new ClassPathResource("\\template\\DeliveryNote.xlsx");

			FileInputStream file = new FileInputStream(resource.getFile());

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// XSSFWorkbook workbook = new XSSFWorkbook();
			// XSSFSheet sheet = workbook.createSheet("Drivers");

			XSSFSheet sheet = workbook.getSheet("DeliveryNote");

			sheet.getRow(6).getCell(5).setCellType(CellType.STRING);
			sheet.getRow(6).getCell(5).setCellValue(customer.id);
			
			sheet.getRow(9).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(9).getCell(0).setCellValue(customer.companyName);
			
			sheet.getRow(10).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(10).getCell(0).setCellValue(customer.buildingFloorUnit+", "+ customer.address);
			
			sheet.getRow(11).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(11).getCell(0).setCellValue("Singapore "+customer.postcode);

			sheet.getRow(12).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(12).getCell(0).setCellValue("Attn Person: "+ customer.contactPerson.toString());
			
			sheet.getRow(13).getCell(0).setCellType(CellType.STRING);
			sheet.getRow(13).getCell(0).setCellValue("Phone: "+customer.phone.toString());
			
			userService.export(workbook, response, "application/vnd.ms-excel");
		}
	  }
	

}
