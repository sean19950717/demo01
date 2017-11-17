package com.demo.modules.quartz.service;

import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.modules.quartz.entity.QuartzJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月21日 上午11:17:26
 */
public interface QuartzJobLogService {

	Page<QuartzJobLogEntity> listForPage(Map<String, Object> params);
	
	R batchRemove(Long[] id);
	
	R batchRemoveAll();
	
}
