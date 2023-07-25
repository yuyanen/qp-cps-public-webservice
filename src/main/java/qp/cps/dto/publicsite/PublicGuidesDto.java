package qp.cps.dto.publicsite;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.dto.FileDto;
import qp.cps.helper.CacheHelper;
import qp.cps.model.PublicGuide;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicGuidesDto {

	public Integer id;

	public Integer ordinal;

	public String title;

	public Boolean toShowInUnitView;

	public FileDto file = new FileDto();

	public static PublicGuidesDto buildDto(CacheHelper cache, PublicGuide model) {
		PublicGuidesDto dto = new PublicGuidesDto();
		dto.id = model.getId();
		dto.ordinal = model.getOrdinal();
		dto.title = model.getTitle();
		dto.toShowInUnitView = model.getToShowInUnitView();
		if (model.getFile() != null) {
			dto.file = FileDto.buildFromFile(model.getFile(), model.getFile().getDocumentType());
		}
		return dto;
	}
}
