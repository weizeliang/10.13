package com.weizeliang.cms.util;
/**
 * 
 * @ClassName: CMSException 
 * @Description: cms的自定义异常
 * @author: weizeliang
 * @date: 2019年9月18日 上午11:09:35
 */
public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public CMSException() {
		
	}
	public CMSException(String message) {
		super(message);		
	}
	
}
