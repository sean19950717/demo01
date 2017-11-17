package com.demo.modules.ge.entity;

import java.io.Serializable;


/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
public class GeTestEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * aaa
	 */
	private Long aaBb;
	
	/**
	 * bbb
	 */
	private String ccDd;
	

	public GeTestEntity() {
		super();
	}

	public void setAaBb(Long aaBb) {
		this.aaBb = aaBb;
	}
	
	public Long getAaBb() {
		return aaBb;
	}
	
	public void setCcDd(String ccDd) {
		this.ccDd = ccDd;
	}
	
	public String getCcDd() {
		return ccDd;
	}
	
}
