package com.mysite.vo;

public class JSONResult {

	private String result; // "success" or "fail"
	private String failMsg;
	private Object data;

	
	
	public JSONResult() {
	}


	public JSONResult(String result, String failMsg, Object data) {
		this.result = result;
		this.failMsg = failMsg;
		this.data = data;
	}

	
	public void success( Object data ) {
		this.result ="success";
		this.failMsg = null;
		this.data = data;
	}
	
	public void fail( String failMsg  ) {
		this.result ="fail";
		this.failMsg = failMsg;
		this.data = null;
	}
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
