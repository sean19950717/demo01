package com.demo.modules.sys.dao;

import com.demo.modules.sys.entity.SysRoleOrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与机构的关系
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月17日 上午11:29:43
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	List<Long> listOrgId(Long roleId);
	
	int batchRemoveByOrgId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
