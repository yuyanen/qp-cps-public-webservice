package qp.cps.dto.publicsite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import qp.cps.annotation.MapProjection;
import qp.cps.dto.FileDto;

public class AnnouncementDto {

	@MapProjection(path = "id")
	public Integer id;

	@MapProjection(path = "title")
	public String title;

	@MapProjection(path = "buttonText")
	public String buttonText;

	@MapProjection(path = "content")
	public String content;

	@MapProjection(path = "type.code")
	public String type;

	@MapProjection(path = "effectiveDate")
	public LocalDate effectiveDate;

	@MapProjection(path = "expiryDate")
	public LocalDate expiryDate;

	@MapProjection(path = "imageFile.id")
	public Integer imageID;

	public FileDto imageFile;

	public List<FileDto> attachments = new ArrayList<>();

	@MapProjection(path = "imageFile.url")
	public String imageUrl;

}
