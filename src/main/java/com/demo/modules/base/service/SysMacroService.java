package com.demo.modules.base.service;

import com.demo.common.entity.R;
import com.demo.modules.base.entity.SysMacroEntity;

import java.util.List;

/**
 * 通用字典
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月15日 下午12:51:35
 */
public interface SysMacroService {

	List<SysMacroEntity> listMacro();
	
	List<SysMacroEntity> listNotMacro();
	
	R saveMacro(SysMacroEntity macro);
	
	R getObjectById(Long id);
	
	R updateMacro(SysMacroEntity macro);
	
	R batchRemove(Long[] id);
	
}
