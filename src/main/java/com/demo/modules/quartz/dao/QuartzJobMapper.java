package com.demo.modules.quartz.dao;

import com.demo.modules.quartz.entity.QuartzJobEntity;
import com.demo.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 定时任务
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月20日 下午11:19:55
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

}
