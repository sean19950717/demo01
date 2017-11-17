package com.demo.modules.quartz.service.impl;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.common.entity.R;
import com.demo.common.utils.CommonUtils;
import com.demo.modules.quartz.entity.QuartzJobLogEntity;
import com.demo.modules.quartz.manager.QuartzJobLogManager;
import com.demo.modules.quartz.service.QuartzJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月21日 上午11:18:22
 */
@Service("quartzJobLogService")
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

	@Autowired
	private QuartzJobLogManager quartzJobLogManager;
	
	@Override
	public Page<QuartzJobLogEntity> listForPage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<QuartzJobLogEntity> page = new Page<>(query);
		quartzJobLogManager.listForPage(page, query);
		return page;
	}

	@Override
	public R batchRemove(Long[] id) {
		int count = quartzJobLogManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R batchRemoveAll() {
		int count = quartzJobLogManager.batchRemoveAll();
		return CommonUtils.msg(count);
	}


}
