package com.demo.modules.test.entity;

import java.io.Serializable;


/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
public class TestEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String aaa;
	
	/**
	 * 
	 */
	private String bbb;
	

	public TestEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	
	public String getAaa() {
		return aaa;
	}
	
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	
	public String getBbb() {
		return bbb;
	}
	
}
