package qp.scs.response.model;

import java.util.List;

import qp.scs.constant.ResponseConstant;

public class ErrorResponseModel<T> extends ResponseModel<T> {

	private List<String> errorMessages;

	public ErrorResponseModel(List<String> messages) {
		super(null);
		this.setStatus(ResponseConstant.FAILURE);
		this.setErrorMessages(messages);
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
