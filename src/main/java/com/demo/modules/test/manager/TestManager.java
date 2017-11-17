package com.demo.modules.test.manager;

import java.util.List;
import com.demo.common.entity.Page;
import com.demo.modules.test.entity.TestEntity;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 上午9:13:52
 */
public interface TestManager {

    int add(TestEntity test);

    int removeLogicalById(Long id);
    int removeLogicalById(List<Long> ids);
    int removePhysicalById(Long id);
    int removePhysicalById(List<Long> ids);
    int removeAll();

    int update(TestEntity test);

	TestEntity getById(Long id);
	TestEntity getByObject(TestEntity test);

    List<TestEntity> list(TestEntity test,Page<TestEntity> page);
    List<TestEntity> list(TestEntity test);
    List<TestEntity> listAll();
}
