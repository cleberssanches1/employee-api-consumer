package br.com.viavarejo.vo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ResponseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2048305421639905114L;
	
	@SerializedName("values")
	private String values;
	@SerializedName("code")
	private String code;
	@SerializedName("message")
	private String message;

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
