package qp.cps.dto;

import qp.cps.annotation.MapProjection;
import qp.cps.constant.Codes;
import qp.cps.model.File;
import qp.cps.model.Type;

public class FileDto {

	@MapProjection(path = "id")
	public Integer fileId;

	@MapProjection(path = "filename")
	public String filename;

	@MapProjection(path = "originalFilename")
	public String originalFilename;

	@MapProjection(path = "fileSize")
	public Integer fileSize;

	@MapProjection(path = "path")
	public String path;

	@MapProjection(path = "extension")
	public String extension;

	@MapProjection(path = "title")
	public String title;

	@MapProjection(path = "hash")
	public String hash;

	@MapProjection(path = "description")
	public String description;

	@MapProjection(path = "documentType.code")
	public String documentTypeKey;

	@MapProjection(path = "url")
	public String url;

	public String contentType;

	public String preSignedUrl;

	public static FileDto buildFromFile(File fileObj, Type docType) {
		FileDto dto = new FileDto();
		dto.fileId = fileObj.getId();
		dto.filename = fileObj.getFilename();
		dto.originalFilename = fileObj.getOriginalFilename();
		dto.fileSize = fileObj.getFileSize();
		dto.path = fileObj.getPath();
		dto.extension = fileObj.getExtension();
		dto.description = fileObj.getDescription();
		dto.documentTypeKey = docType.getCode().equals(Codes.MediaTypes.TEMP) ? null : docType.getCode();
		dto.url = fileObj.getUrl();
		dto.hash = fileObj.getHash();
		return dto;
	}

	public Integer getFileId() {
		return fileId;
	}

}
