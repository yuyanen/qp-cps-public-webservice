package qp.cps.dto.publicsite;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import qp.cps.dto.SearchDto;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class BulletinSearchDto extends SearchDto {
//
//	public Integer year;
//
//	public LocalDate publishDateFrom;
//
//	public LocalDate publishDateTo;
//
//	public String bulletinType;
//
//}

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulletinSearchDto extends SearchDto {
    public Integer year;
    public LocalDate publishDateFrom;
    public LocalDate publishDateTo;
    public String bulletinType;
    
    // getters
    public Integer getYear() {
        return year;
    }
    
    public LocalDate getPublishDateFrom() {
        return publishDateFrom;
    }

    public LocalDate getPublishDateTo() {
        return publishDateTo;
    }
    
    public String getBulletinType() {
        return bulletinType;
    }

    // setters
    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPublishDateFrom(LocalDate publishDateFrom) {
        this.publishDateFrom = publishDateFrom;
    }
    
    public void setPublishDateTo(LocalDate publishDateTo) {
        this.publishDateTo = publishDateTo;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }
}

