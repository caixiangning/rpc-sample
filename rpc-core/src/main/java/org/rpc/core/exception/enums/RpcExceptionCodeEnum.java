package org.rpc.core.exception.enums;
/**
 *
 * 
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
public enum RpcExceptionCodeEnum {
	DATA_PARSER_ERROR("DATA_PARSER_ERROR","数据转换异常"),
    NO_BEAN_FOUND("NO_BEAN_FOUND","没有找到bean对象"),
    INVOKE_REQUEST_ERROR("INVOKE_REQUEST_ERROR","RPC请求异常");
	
	private String code;
	private String message;
	
	private RpcExceptionCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
