package com.demo.modules.test.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.common.entity.Page;
import com.demo.modules.test.dao.TestMapper;
import com.demo.modules.test.entity.TestEntity;
import com.demo.modules.test.manager.TestManager;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
@Component("testManager")
public class TestManagerImpl implements TestManager {

	@Autowired
	private TestMapper testMapper;


    @Override
    public int add(TestEntity test) {
        return testMapper.insertSelective(test);
    }

    @Override
    public int removeLogicalById(Long id) {
        throw new RuntimeException("逻辑删除需要根据业务自行处理");
    }

    @Override
    public int removeLogicalById(List<Long> ids) {
        throw new RuntimeException("逻辑删除需要根据业务自行处理");
    }

    @Override
    public int removePhysicalById(Long id) {
        return testMapper.deleteById(id);
    }

    @Override
    public int removePhysicalById(List<Long> ids) {
        return testMapper.deleteByIds(ids);
    }

    @Override
    public int removeAll() {
        return testMapper.deleteAll();
    }

    @Override
    public int update(TestEntity test) {
        return testMapper.updateByIdSelective(test);
    }

    @Override
    public TestEntity getById(Long id) {
        return testMapper.selectOneById(id);
    }

    @Override
    public TestEntity getByObject(TestEntity test) {
        return testMapper.selectOneByObject(test);
    }

    @Override
    public List<TestEntity> list(TestEntity test, Page<TestEntity> page) {
        return testMapper.selectByObject(test,page);
    }

    @Override
    public List<TestEntity> list(TestEntity test) {
        return testMapper.selectByObject(test,null);
    }

    @Override
    public List<TestEntity> listAll() {
        return testMapper.selectAll();
    }
}
