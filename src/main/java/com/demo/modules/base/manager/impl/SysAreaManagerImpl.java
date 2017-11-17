package com.demo.modules.base.manager.impl;

import com.demo.common.entity.Query;
import com.demo.common.utils.CommonUtils;
import com.demo.modules.base.dao.SysAreaMapper;
import com.demo.modules.base.entity.SysAreaEntity;
import com.demo.modules.base.manager.SysAreaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 行政区域
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月18日 下午3:39:35
 */
@Component("sysAreaManager")
public class SysAreaManagerImpl implements SysAreaManager {

	@Autowired
	private SysAreaMapper sysAreaMapper;
	
	@Override
	public List<SysAreaEntity> listAreaByParentCode(Query query) {
		return sysAreaMapper.listAreaByParentCode(query);
	}

	@Override
	public int saveArea(SysAreaEntity area) {
		return sysAreaMapper.save(area);
	}

	@Override
	public SysAreaEntity getAreaById(Long areaId) {
		return sysAreaMapper.getObjectById(areaId);
	}

	@Override
	public int updateArea(SysAreaEntity area) {
		return sysAreaMapper.update(area);
	}

	@Override
	public int batchRemoveArea(Long[] id) {
		return sysAreaMapper.batchRemove(id);
	}
	
	@Override
	public boolean hasChildren(Long[] id) {
		for(Long typeId : id) {
			int count = sysAreaMapper.countAreaChildren(typeId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}
	
}
