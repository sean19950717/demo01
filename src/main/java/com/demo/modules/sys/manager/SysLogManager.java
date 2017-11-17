package com.demo.modules.sys.manager;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.sys.entity.SysLogEntity;

import java.util.List;

/**
 * 系统日志
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月14日 下午8:43:06
 */
public interface SysLogManager {

	void saveLog(SysLogEntity log);
	
	List<SysLogEntity> listLog(Page<SysLogEntity> page, Query query);
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();

}
