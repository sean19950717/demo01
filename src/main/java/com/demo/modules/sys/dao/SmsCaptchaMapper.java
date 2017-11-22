package com.demo.modules.sys.dao;

import com.demo.modules.sys.entity.SmsCaptcha;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsCaptchaMapper {
    int deleteByPrimaryKey(Integer smsCapachaId);

    int insert(SmsCaptcha record);

    int insertSelective(SmsCaptcha record);

    SmsCaptcha selectByPrimaryKey(Integer smsCapachaId);

    int updateByPrimaryKeySelective(SmsCaptcha record);

    int updateByPrimaryKey(SmsCaptcha record);
}