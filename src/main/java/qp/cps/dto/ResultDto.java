package qp.cps.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto<M> {

	public Integer total;

	public Integer noOfPages;

	@JsonIgnore
	public List<M> models = new ArrayList<>();

	public List<Object> items;

	public Object result;

	public String successFlag;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
