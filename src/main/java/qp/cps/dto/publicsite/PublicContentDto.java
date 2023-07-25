package qp.cps.dto.publicsite;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.model.PublicContent;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicContentDto {

	public String code;

	public String content;

	public LocalDateTime updatedDate;

	public static PublicContentDto buildDto(PublicContent publicContent) {
		if (publicContent != null) {
			PublicContentDto dto = new PublicContentDto();
			dto.content = publicContent.getContent();
			dto.updatedDate = publicContent.getUpdatedDate();
			return dto;
		} else {
			return null;
		}

	}
}
