package com.demo.modules.ge.controller;

import java.util.Arrays;
import java.util.Map;

import com.demo.common.exception.ErrorKeyEnum;
import com.demo.common.exception.Preconditions;
import com.demo.common.exception.Response;
import com.demo.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.demo.common.annotation.SysLog;
import com.demo.common.entity.R;
import com.demo.modules.sys.controller.AbstractController;
import org.springframework.web.bind.annotation.RestController;

import com.demo.modules.ge.entity.GeTestEntity;
import com.demo.modules.ge.service.GeTestService;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
@RestController
@RequestMapping("/ge")
public class GeTestController extends AbstractController {
	
	@Autowired
	private GeTestService geTestService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping(path="/list",consumes = "application/json")
	public Response list(@RequestBody Map<String, Object> params) {
		Preconditions.checkNotNull(null, ErrorKeyEnum.COMMON_DELETE_FAIL);
		return Response.success(geTestService.listGeTest(params));
	}
		
	/**
	 * 新增
	 * @param geTest
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody GeTestEntity geTest) {
        int num = geTestService.add(geTest);
        return CommonUtils.msg(num);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		GeTestEntity geTestEntity = geTestService.getById(id);
        return CommonUtils.msg(geTestEntity);
	}
	
	/**
	 * 修改
	 * @param geTest
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody GeTestEntity geTest) {
		int num = geTestService.update(geTest);
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
        int num = geTestService.removePhysicalById(Arrays.asList(id));
        return CommonUtils.msg(num);
	}
	
}
