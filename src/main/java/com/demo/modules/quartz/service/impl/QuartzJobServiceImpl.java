package com.demo.modules.quartz.service.impl;

import com.demo.common.constant.SystemConstant;
import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.common.entity.R;
import com.demo.common.utils.CommonUtils;
import com.demo.modules.quartz.entity.QuartzJobEntity;
import com.demo.modules.quartz.manager.QuartzJobManager;
import com.demo.modules.quartz.service.QuartzJobService;
import com.demo.modules.quartz.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月20日 下午11:49:18
 */
@Service("quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {
	
	@Autowired
	private QuartzJobManager quartzJobManager;
	
	/**
	 * 项目启动，初始化任务
	 */
	@PostConstruct
	public void init() {
		List<QuartzJobEntity> jobList = quartzJobManager.listNormalJob();
		for(QuartzJobEntity job : jobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(job.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(job);
            }else {
                ScheduleUtils.updateScheduleJob(job);
            }
		}
	}

	@Override
	public Page<QuartzJobEntity> list(Map<String, Object> params) {
		Query query = new Query(params);
		Page<QuartzJobEntity> page = new Page<>(query);
		quartzJobManager.listForPage(page, query);
		return page;
	}

	@Override
	public R saveQuartzJob(QuartzJobEntity job) {
		job.setStatus(SystemConstant.ScheduleStatus.NORMAL.getValue());
		int count = quartzJobManager.saveQuartzJob(job);
		ScheduleUtils.createScheduleJob(job);
		return CommonUtils.msg(count);
	}

	@Override
	public R getQuartzJobById(Long jobId) {
		QuartzJobEntity job = quartzJobManager.getQuartzJobById(jobId);
		return CommonUtils.msg(job);
	}

	@Override
	public R updateQuartzJob(QuartzJobEntity job) {
		int count = quartzJobManager.updateQuartzJob(job);
		ScheduleUtils.updateScheduleJob(job);
		return CommonUtils.msg(count);
	}

	@Override
	public R batchRemoveQuartzJob(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.deleteScheduleJob(jobId);
		}
		int count = quartzJobManager.batchRemoveQuartzJob(id);
		return CommonUtils.msg(id, count);
	}
	
	@Override
	public R run(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.run(quartzJobManager.getQuartzJobById(jobId));
		}
		return CommonUtils.msg(1);
	}
	
	@Override
	public R pause(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.pauseJob(jobId);
		}
		int count = quartzJobManager.batchUpdate(id, SystemConstant.ScheduleStatus.PAUSE.getValue());
		return CommonUtils.msg(id, count);
	}
	
	@Override
	public R resume(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.resumeJob(jobId);
		}
		int count = quartzJobManager.batchUpdate(id, SystemConstant.ScheduleStatus.NORMAL.getValue());
		return CommonUtils.msg(id, count);
	}

}
