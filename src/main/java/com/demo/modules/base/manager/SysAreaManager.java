package com.demo.modules.base.manager;

import com.demo.common.entity.Query;
import com.demo.modules.base.entity.SysAreaEntity;

import java.util.List;

/**
 * 行政区域
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月18日 下午3:39:00
 */
public interface SysAreaManager {

	List<SysAreaEntity> listAreaByParentCode(Query query);
	
	int saveArea(SysAreaEntity area);
	
	SysAreaEntity getAreaById(Long areaId);
	
	int updateArea(SysAreaEntity area);
	
	int batchRemoveArea(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
