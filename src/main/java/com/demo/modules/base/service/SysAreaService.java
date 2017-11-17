package com.demo.modules.base.service;

import com.demo.common.entity.R;
import com.demo.modules.base.entity.SysAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月18日 下午3:40:18
 */
public interface SysAreaService {

	List<SysAreaEntity> listAreaByParentCode(String areaCode);
	
	R listAreaByParentCode(Map<String, Object> params);
	
	R saveArea(SysAreaEntity area);
	
	R getAreaById(Long areaId);
	
	R updateArea(SysAreaEntity area);
	
	R batchRemoveArea(Long[] id);
	
}
