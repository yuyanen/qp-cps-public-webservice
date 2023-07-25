package qp.cps.controller.publicSite;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import qp.cps.controller.BaseController;
import qp.cps.dto.FileDto;
import qp.cps.dto.ResultDto;
import qp.cps.dto.publicsite.AnnouncementDto;
import qp.cps.dto.publicsite.BulletinSearchDto;
import qp.cps.model.File;
import qp.cps.model.PublicAnnouncement;
import qp.cps.repository.AnnouncementRepository;
import qp.cps.repository.FileRepository;

@RestController
@RequestMapping(path = "/api/announcements/public")
@Transactional
public class AnnouncementController extends BaseController {

	@Autowired
	AnnouncementRepository announcementRepository;
	@Autowired
	FileRepository fileRepository;
	
	@RequestMapping(value = "/search-bulletin-board", method = RequestMethod.GET)
	public ResultDto<AnnouncementDto> searchAnnouncements(BulletinSearchDto searchDto) {
		ResultDto<AnnouncementDto> announcementDtoList = announcementRepository.searchAnnouncements(searchDto);
		// populate and return ResultDTO
//		        List<AnnouncementDto> finalResults = Lists.newArrayList();
//		   
//		        
//		        AnnouncementDto annoDto = new AnnouncementDto();
//		        annoDto.id=1;
//		        annoDto.buttonText="Meow";
//		        annoDto.title ="Business";
//		        annoDto.imageUrl="https://hdb-cps-public.s3.ap-southeast-1.amazonaws.com/DOC_ANNOUNCEMENT_BANNER/188";
//		        annoDto.type ="ANNC_TOP_BANNER";
//		        
//		        AnnouncementDto annoDto2 = new AnnouncementDto();
//		        annoDto2.id=2;
//		        annoDto2.buttonText="Meow 2";
//		        annoDto2.title ="Business 2";
//		        annoDto2.imageUrl="https://hdb-cps-public.s3.ap-southeast-1.amazonaws.com/DOC_ANNOUNCEMENT_BANNER/188";
//		        annoDto2.type ="ANNC_TOP_BANNER";
//		        
//		        finalResults.add(annoDto);
//		        finalResults.add(annoDto2);
//
//				ResultDto<AnnouncementDto> resultDTO = new ResultDto<>();
//				resultDTO.models = finalResults;
//
//				List<Object> items = new ArrayList<>();
//				resultDTO.items = items;
//				finalResults.forEach(model -> items.add(model));
//
//				resultDTO.total = 3;
//				resultDTO.noOfPages = 1;
//				return resultDTO;
				
	return announcementDtoList;
	}

	@RequestMapping(value = "/search-bulletin-board/view/{bulletinUnitId}", method = RequestMethod.GET)
	public AnnouncementDto getBulletinUnitView(@PathVariable Integer bulletinUnitId) {
		AnnouncementDto announcementDto = announcementRepository.getBulletinView(bulletinUnitId);

		List<Integer> fileIds = new ArrayList<Integer>();
		PublicAnnouncement announcement = new PublicAnnouncement();
		announcement = announcementRepository.get(PublicAnnouncement.class, announcementDto.id);
		for (File file : announcement.getFiles()) {
			fileIds.add(file.getId());
		}
		if (fileIds.size() > 0) {
			List<FileDto> fileDtos = announcementRepository.getAnnouncementAttachment(fileIds);
			announcementDto.attachments = fileDtos;
		}

		return announcementDto;
	}

}
