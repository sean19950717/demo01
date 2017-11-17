package com.demo.modules.test.controller;

import java.util.Arrays;
import java.util.Map;

import com.demo.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.demo.common.annotation.SysLog;
import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.modules.sys.controller.AbstractController;;

import com.demo.modules.test.entity.TestEntity;
import com.demo.modules.test.service.TestService;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
@Controller
@RequestMapping("/test")
public class TestController extends AbstractController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestBody Map<String, Object> params) {
        Page page = new Page();
        page.setRows(testService.listAll());
        return CommonUtils.msg(page);
	}
		
	/**
	 * 新增
	 * @param test
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody TestEntity test) {
        int num = testService.add(test);
        return CommonUtils.msg(num);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		TestEntity testEntity = testService.getById(id);
        return CommonUtils.msg(testEntity);
	}
	
	/**
	 * 修改
	 * @param test
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody TestEntity test) {
		int num = testService.update(test);
        return CommonUtils.msg(num);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
        int num = testService.removePhysicalById(Arrays.asList(id));
        return CommonUtils.msg(num);
	}
	
}
