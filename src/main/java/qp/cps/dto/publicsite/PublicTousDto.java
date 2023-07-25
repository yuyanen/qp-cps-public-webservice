package qp.cps.dto.publicsite;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.helper.CacheHelper;
import qp.cps.model.PublicTou;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicTousDto {

	public Integer id;

	public String typeCode;

	public String typeLabel;

	public Integer ordinal;

	public String title;

	public String content;

	public String summary;

	public LocalDateTime updatedDate;

	public static PublicTousDto buildDto(CacheHelper cache, PublicTou model) {
		PublicTousDto dto = new PublicTousDto();
		dto.id = model.getId();
		dto.typeCode = model.getType().getCode();
		dto.typeLabel = model.getType().getLabel();
		dto.ordinal = model.getOrdinal();
		dto.title = model.getTitle();
		dto.content = model.getContent();
		dto.summary = model.getSummary();
		dto.updatedDate = model.getUpdatedDate();
		return dto;
	}
}
