package com.app.dto;

public class ResponseDt<T> {
	private String status;
	private T data;
	
	
	
	public ResponseDt() {
		
		// TODO Auto-generated constructor stub
	}



	public ResponseDt(String status, T data) {
		super();
		this.status = status;
		this.data = data;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	@Override
	public String toString() {
		return "ResponseDt [status=" + status + ", data=" + data + "]";
	}
	
	
	
	
}
