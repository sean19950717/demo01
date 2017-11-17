package com.demo.modules.generator.controller;

import com.demo.common.entity.Page;
import com.demo.modules.generator.entity.GeneratorParamEntity;
import com.demo.modules.generator.entity.TableEntity;
import com.demo.modules.generator.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:56:30
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	private final Logger logger= LoggerFactory.getLogger(SysGeneratorController.class);
    @Value(value = "${generator.project.basePath}")
	private  String projectBasePath;
	@Autowired
	private SysGeneratorService sysGeneratorService;


	/**
	 * 表格列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<TableEntity> list(@RequestBody Map<String, Object> params) {
		return sysGeneratorService.listTable(params);
	}
	
	/**
	 * 生成代码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/code")
	public void generator(GeneratorParamEntity params, HttpServletRequest request, HttpServletResponse response) throws IOException {
		params.setProjectBasePath(projectBasePath);
		byte[] code = sysGeneratorService.generator(params);
		String attachment = "attachment; filename=" + params.getTables()[0] + ".zip";
		response.reset();  
        response.setHeader("Content-Disposition", attachment);  
        response.addHeader("Content-Length", "" + code.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
        IOUtils.write(code, response.getOutputStream());
	}
	
}
