package qp.cps.dto.publicsite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import qp.cps.annotation.MapProjection;
import qp.cps.dto.FileDto;
import qp.cps.model.PublicAnnouncement;


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

	public AnnouncementDto() {
    }

    public AnnouncementDto(PublicAnnouncement publicAnnouncement) {
        if (publicAnnouncement != null) {
            this.id = publicAnnouncement.getId();
            this.title = publicAnnouncement.getTitle();
            this.buttonText = publicAnnouncement.getButtonText();
            this.content = publicAnnouncement.getContent();
            this.type = publicAnnouncement.getType() != null ? publicAnnouncement.getType().getCode() : null;
            this.effectiveDate = publicAnnouncement.getEffectiveDate();
            this.expiryDate = publicAnnouncement.getExpiryDate();
            this.imageID = publicAnnouncement.getImageFile() != null ? publicAnnouncement.getImageFile().getId() : null;
            this.imageUrl = publicAnnouncement.getImageFile() != null ? publicAnnouncement.getImageFile().getUrl() : null;

//            // Assume FileDto has a constructor that accepts File
//            this.imageFile = publicAnnouncement.getImageFile() != null ? new FileDto(publicAnnouncement.getImageFile()) : null;
//
//            // Convert each file to FileDto and add it to the list
//            if (publicAnnouncement.getFiles() != null) {
//                this.attachments = publicAnnouncement.getFiles().stream()
//                        .map(FileDto::new)
//                        .collect(Collectors.toList());
//            }
        }
    }
}
