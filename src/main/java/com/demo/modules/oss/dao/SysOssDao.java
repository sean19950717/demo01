package com.demo.modules.oss.dao;


import com.demo.modules.oss.entity.SysOssEntity;
import com.demo.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * 
 * @author Centling Technologies
 * @email xxx@demo.com
 * @date 2017-03-25 12:13:26
 */
@Mapper
public interface SysOssDao extends BaseMapper<SysOssEntity> {
    public int deleteById(int ids[]);
	
}
