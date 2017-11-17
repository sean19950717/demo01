package com.demo.modules.ge.dao;

import com.demo.common.entity.Page;

import com.demo.modules.ge.entity.GeTestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.demo.common.entity.Query;
/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
@Mapper
public interface GeTestMapper {
    int insert(GeTestEntity record);

    int insertSelective(GeTestEntity record);

    int insertInBatch(List<GeTestEntity> records);

    int deleteById(Long id);

    int deleteByObject(GeTestEntity record);

    int deleteByIds(List<Long> ids);

    int deleteAll();

    int updateById(GeTestEntity record);

    int updateByIdSelective(GeTestEntity record);

    GeTestEntity selectOneById(Long id);

    GeTestEntity selectOneByObject(GeTestEntity record);

    List<GeTestEntity> selectByObject(@Param("entity") GeTestEntity record, Page page);

    List<GeTestEntity> selectAll();

    List<GeTestEntity> listForPage(Page<GeTestEntity> page, Query query);

    long count(GeTestEntity record);

    long countAll();
	
}
