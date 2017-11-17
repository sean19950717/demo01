package com.demo.modules.quartz.service;

import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.modules.quartz.entity.QuartzJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月20日 下午11:48:32
 */
public interface QuartzJobService {
	
	Page<QuartzJobEntity> list(Map<String, Object> params);
	
	R saveQuartzJob(QuartzJobEntity job);
	
	R getQuartzJobById(Long jobId);
	
	R updateQuartzJob(QuartzJobEntity job);
	
	R batchRemoveQuartzJob(Long[] id);
	
	R run(Long[] id);
	
	R pause(Long[] id);
	
	R resume(Long[] id);
	
}
