package qp.scs.response.model;

import qp.scs.constant.ResponseConstant;

public class ResponseModel<T> {
	
	private T data;
	
	private String status = ResponseConstant.SUCCESS;
	
	private String message;
	
	
	public ResponseModel(T data, String status, String message){
		
		this.data = data;
		this.status = status;
		this.message = message;
	}
	
	public ResponseModel(T data){
		
		this.data = data;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
