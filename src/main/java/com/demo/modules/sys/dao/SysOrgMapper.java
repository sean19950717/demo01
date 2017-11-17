package com.demo.modules.sys.dao;

import com.demo.modules.sys.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织架构
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月17日 上午11:26:05
 */
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {

	int countOrgChildren(Long parentId);
	
}
