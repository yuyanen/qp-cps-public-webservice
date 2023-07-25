package qp.cps.dto.publicsite;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.dto.FileDto;
import qp.cps.helper.CacheHelper;
import qp.cps.model.PublicFaq;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaqDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Integer id;

	public String createdBy;

	public LocalDateTime createdDate;

	public String updatedBy;

	public LocalDateTime updatedDate;

	public String title;

	public String content;

	public String originalFilename;

	public Integer ordinal;

	public String description;

	public FileDto file;

	public static FaqDto buildDto(CacheHelper cache, PublicFaq publicFaq) {
		FaqDto dto = new FaqDto();
		dto.id = publicFaq.getId();
		dto.createdBy = publicFaq.getCreatedBy();
		dto.ordinal = publicFaq.getOrdinal();
		dto.title = publicFaq.getTitle();
		dto.content = publicFaq.getContent();
		try {
			dto.file = FileDto.buildFromFile(publicFaq.getFile(), publicFaq.getFile().getDocumentType());
			dto.description = dto.file.description;
		} catch (Exception ex) {
			dto.file = null;
		}

		if (null != dto.file && null != dto.file.originalFilename && !dto.file.originalFilename.trim().equals("")) {
			dto.originalFilename = dto.file.originalFilename;
		} else {
			dto.originalFilename = "";
		}

		return dto;
	}
}
