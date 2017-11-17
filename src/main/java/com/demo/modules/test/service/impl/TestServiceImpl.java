package com.demo.modules.test.service.impl;

        import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.common.entity.Page;

import com.demo.modules.test.entity.TestEntity;
import com.demo.modules.test.manager.TestManager;
import com.demo.modules.test.service.TestService;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestManager testManager;

    @Override
    public int add(TestEntity test) {
        return testManager.add(test);
    }

    @Override
    public int removeLogicalById(Long id) {
        return testManager.removeLogicalById(id);
    }

    @Override
    public int removeLogicalById(List<Long> ids) {
        return testManager.removeLogicalById(ids);
    }

    @Override
    public int removePhysicalById(Long id) {
        return testManager.removePhysicalById(id);
    }

    @Override
    public int removePhysicalById(List<Long> ids) {
        return testManager.removePhysicalById(ids);
    }

    @Override
    public int removeAll() {
        return testManager.removeAll();
    }

    @Override
    public int update(TestEntity test) {
        return testManager.update(test);
    }

    @Override
    public TestEntity getById(Long id) {
        return testManager.getById(id);
    }

    @Override
    public TestEntity getByObject(TestEntity test) {
        return testManager.getByObject(test);
    }

    @Override
    public Page<TestEntity> list(TestEntity test, Page<TestEntity> page) {
		testManager.list(test, page);
        return page;
    }

    @Override
    public List<TestEntity> list(TestEntity test) {
        return testManager.list(test);
    }

    @Override
    public List<TestEntity> listAll() {
        return testManager.listAll();
    }
}
