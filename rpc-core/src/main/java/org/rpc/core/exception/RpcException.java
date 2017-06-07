package org.rpc.core.exception;
/**
 *
 * RPC框架异常类
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
@SuppressWarnings("serial")
public class RpcException extends Exception{
	
	// 异常编码
	private String code;
	
	// 异常详细信息
	private Object data;

	public RpcException(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public RpcException(String message, Throwable cause, String code, Object data) {
		//this(code, data);
		super(message, cause);
		// TODO Auto-generated constructor stub
		this.code = code;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
