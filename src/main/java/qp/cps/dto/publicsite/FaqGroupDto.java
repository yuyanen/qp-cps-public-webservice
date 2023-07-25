package qp.cps.dto.publicsite;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.helper.CacheHelper;
import qp.cps.model.PublicFaq;
import qp.cps.model.PublicFaqGroup;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaqGroupDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Integer headingId;

	public String createdBy;

	public LocalDateTime createdDate;

	public String updatedBy;

	public LocalDateTime updatedDate;

	public LocalDate effectiveDate;

	public LocalDate expiryDate;

	public String heading;

	public Integer ordinal;

	public List<FaqDto> faq;

	@SuppressWarnings("static-access")
	public static FaqGroupDto buildDto(CacheHelper cache, PublicFaqGroup publicFaq) {
		FaqGroupDto dto = new FaqGroupDto();

		List<FaqDto> faqList = new ArrayList<FaqDto>();

		dto.headingId = publicFaq.getId();
		dto.createdBy = publicFaq.getCreatedBy();
		dto.ordinal = publicFaq.getOrdinal();
		dto.effectiveDate = publicFaq.getEffectiveDate();
		dto.expiryDate = publicFaq.getExpiryDate();
		dto.heading = publicFaq.getName();
		for (PublicFaq faqs : publicFaq.getPublicFaqs()) {
			FaqDto faqClass = new FaqDto();
			faqList.add(faqClass.buildDto(cache, faqs));
		}
		dto.faq = faqList;
		return dto;
	}
}
