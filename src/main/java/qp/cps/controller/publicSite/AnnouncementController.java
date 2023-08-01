package qp.cps.controller.publicSite;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import qp.cps.response.model.common.PageModel;

import qp.cps.controller.BaseController;
import qp.cps.controller.CustomerController;
import qp.cps.dto.FileDto;
import qp.cps.dto.ResultDto;
import qp.cps.dto.publicsite.AnnouncementDto;
import qp.cps.dto.publicsite.BulletinSearchDto;
import qp.cps.model.File;
import qp.cps.model.PublicAnnouncement;
import qp.cps.repository.AnnouncementRepository;
import qp.cps.service.AnnouncementService;
import qp.cps.repository.FileRepository;
import qp.cps.util.PaginationUtil;
import org.springframework.data.domain.Pageable;
import qp.cps.util.Converter;
@RestController
@RequestMapping(path = "/api/announcements/public")
@Transactional
public class AnnouncementController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AnnouncementController.class);

	@Autowired
	AnnouncementRepository announcementRepository;
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	AnnouncementService anncService; 
	
//	@RequestMapping(value = "/search-bulletin-board", method = RequestMethod.GET)
//	public ResultDto<AnnouncementDto> searchAnnouncements(BulletinSearchDto searchDto) {
//		ResultDto<AnnouncementDto> announcementDtoList = announcementRepository.searchAnnouncements(searchDto);
//		List<Integer> fileIds = new ArrayList<Integer>();
//		fileIds.add(3);
//		Integer b = fileIds.get(100);
//	return announcementDtoList;
//	}

//	@RequestMapping(value = "/search-bulletin-board/view/{bulletinUnitId}", method = RequestMethod.GET)
//	public AnnouncementDto getBulletinUnitView(@PathVariable Integer bulletinUnitId) {
//		AnnouncementDto announcementDto = announcementRepository.getBulletinView(bulletinUnitId);
//
//		List<Integer> fileIds = new ArrayList<Integer>();
//		PublicAnnouncement announcement = new PublicAnnouncement();
//		announcement = announcementRepository.get(PublicAnnouncement.class, announcementDto.id);
//		for (File file : announcement.getFiles()) {
//			fileIds.add(file.getId());
//		}
//		if (fileIds.size() > 0) {
//			List<FileDto> fileDtos = announcementRepository.getAnnouncementAttachment(fileIds);
//			announcementDto.attachments = fileDtos;
//		}
//
//		return announcementDto;
//	}
//	
	// my own method
	@RequestMapping(value = "/search-bulletin-board", method = RequestMethod.GET)
	public ResultDto<AnnouncementDto> searchAnnouncements(BulletinSearchDto searchDto){

	    PageModel<PublicAnnouncement> resultModel = anncService.searchAnnouncements(searchDto);
	    logger.info("testing url " +searchDto.bulletinType);

	    // populate and return ResultDTO
	    List<PublicAnnouncement> results = resultModel.getResult();
//	    logger.info("testing url " +results.get(0).getImageFile().getUrl());
        List<AnnouncementDto> finalResults = Lists.newArrayList();
   
        for (PublicAnnouncement announcement : results) {
        	AnnouncementDto dto = new AnnouncementDto(announcement);
            finalResults.add(dto);
        }        
        
		ResultDto<AnnouncementDto> resultDTO = new ResultDto<>();
		resultDTO.models = finalResults;

		List<Object> items = new ArrayList<>();
		resultDTO.items = items;
		finalResults.forEach(model -> items.add(model));

		resultDTO.total = (int) resultModel.getTotalCount();
		resultDTO.noOfPages = Math.max(0,resultModel.getCurrentPage()+1);
		return resultDTO;

	}

	@RequestMapping(value = "/search-bulletin-board/view/{bulletinUnitId}", method = RequestMethod.GET)
	public AnnouncementDto getBulletinUnitView(@PathVariable Integer bulletinUnitId) {
		PublicAnnouncement annc = anncService.getBulletinView(bulletinUnitId);

    	AnnouncementDto dto = new AnnouncementDto(annc);

		return dto;
	}

}
