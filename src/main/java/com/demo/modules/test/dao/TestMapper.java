package com.demo.modules.test.dao;

import com.demo.common.entity.Page;

import com.demo.modules.test.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
@Mapper
public interface TestMapper {
    int insert(TestEntity record);

    int insertSelective(TestEntity record);

    int insertInBatch(List<TestEntity> records);

    int deleteById(Long id);

    int deleteByObject(TestEntity record);

    int deleteByIds(List<Long> ids);

    int deleteAll();

    int updateById(TestEntity record);

    int updateByIdSelective(TestEntity record);

    TestEntity selectOneById(Long id);

    TestEntity selectOneByObject(TestEntity record);

    List<TestEntity> selectByObject(@Param("entity") TestEntity record, Page page);

    List<TestEntity> selectAll();

    long count(TestEntity record);

    long countAll();
	
}
