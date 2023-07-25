package qp.cps.dto.publicsite;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.dto.SearchDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulletinSearchDto extends SearchDto {

	public Integer year;

	public LocalDate publishDateFrom;

	public LocalDate publishDateTo;

	public String bulletinType;

}
