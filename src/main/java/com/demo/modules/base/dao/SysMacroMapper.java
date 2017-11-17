package com.demo.modules.base.dao;

import com.demo.modules.base.entity.SysMacroEntity;
import com.demo.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 通用字典
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月15日 下午12:46:31
 */
@Mapper
public interface SysMacroMapper extends BaseMapper<SysMacroEntity> {

	List<SysMacroEntity> listNotMacro();
	
	int countMacroChildren(Long typeId);
	
}
