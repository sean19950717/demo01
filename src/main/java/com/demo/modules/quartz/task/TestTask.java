package com.demo.modules.quartz.task;

import org.springframework.stereotype.Component;

/**
 * 测试任务
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月21日 上午1:09:44
 */
@Component("testTask")
public class TestTask {

	/**
	 * 测试方法
	 */
	public void test() {
		System.out.println("current timestapmp is : " + System.currentTimeMillis());
	}
	
}
