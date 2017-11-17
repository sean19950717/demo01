package com.demo.modules.generator.service;

import com.demo.common.entity.Page;
import com.demo.modules.generator.entity.GeneratorParamEntity;
import com.demo.modules.generator.entity.TableEntity;

import java.util.Map;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:55:29
 */
public interface SysGeneratorService {

	Page<TableEntity> listTable(Map<String, Object> params);
	
	byte[] generator(GeneratorParamEntity params);
	
}
