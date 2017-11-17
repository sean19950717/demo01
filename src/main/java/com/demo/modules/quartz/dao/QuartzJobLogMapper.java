package com.demo.modules.quartz.dao;

import com.demo.modules.quartz.entity.QuartzJobLogEntity;
import com.demo.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月20日 下午11:04:51
 */
@Mapper
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

	int batchRemoveAll();
	
}
