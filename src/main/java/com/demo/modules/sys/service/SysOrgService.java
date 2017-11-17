package com.demo.modules.sys.service;

import com.demo.common.entity.R;
import com.demo.modules.sys.entity.SysOrgEntity;

import java.util.List;

/**
 * 组织机构
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月17日 上午11:32:55
 */
public interface SysOrgService {

	List<SysOrgEntity> listOrg();
	
	List<SysOrgEntity> listOrgTree();
	
	R saveOrg(SysOrgEntity org);
	
	R getOrg(Long orgId);
	
	R updateOrg(SysOrgEntity org);
	
	R bactchRemoveOrg(Long[] id);
	
}
