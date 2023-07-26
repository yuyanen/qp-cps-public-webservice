package qp.cps.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import qp.cps.dto.FileDto;
import qp.cps.dto.ResultDto;
import qp.cps.dto.publicsite.AnnouncementDto;
import qp.cps.dto.publicsite.BulletinSearchDto;
import qp.cps.model.File;
import qp.cps.model.PublicAnnouncement;

@Repository
public class AnnouncementRepository extends BaseRepository {

	public ResultDto<AnnouncementDto> searchAnnouncements(BulletinSearchDto searchDto) {
		DetachedCriteria dc = DetachedCriteria.forClass(PublicAnnouncement.class);
		addGe(dc, "expiryDate", LocalDate.now());

		if (searchDto.year != null) {
			addGe(dc, "effectiveDate", LocalDate.of(searchDto.year, 1, 1));
			addLe(dc, "effectiveDate", LocalDate.of(searchDto.year, 12, 31));
		} else {
			addGe(dc, "expiryDate", LocalDate.now());
			addLe(dc, "effectiveDate", LocalDate.now());
		}

		if (searchDto.publishDateFrom != null) {
			addGe(dc, "effectiveDate", searchDto.publishDateFrom);
		}
		if (searchDto.publishDateTo != null) {
			addLe(dc, "effectiveDate", searchDto.publishDateTo);
		}

		addEq(dc, "type.code", searchDto.bulletinType);

		dc.addOrder(Order.desc("effectiveDate"));
		dc.addOrder(Order.desc("createdDate"));

		addDtoProjections(dc, AnnouncementDto.class);

		Boolean toPaginate = Boolean.TRUE;
		if (searchDto.getPageSize() == null) {
			return search(dc, searchDto, Boolean.FALSE);
		}

		return search(dc, searchDto, toPaginate);
	}

	public AnnouncementDto getBulletinView(Integer bulletinUnitId) {
		DetachedCriteria dc = DetachedCriteria.forClass(PublicAnnouncement.class);
		addEq(dc, "id", bulletinUnitId);
		addDtoProjections(dc, AnnouncementDto.class);

		return getFirst(dc);

	}

	public List<FileDto> getAnnouncementAttachment(List<Integer> fileIds) {
		DetachedCriteria dc = DetachedCriteria.forClass(File.class);
		dc.add(Restrictions.in("id", fileIds));
		dc.add(Restrictions.eq("isDeleted", false));
		addDtoProjections(dc, FileDto.class);
		return getList(dc);
	}
}
