package com.demo.modules.ge.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.ge.entity.GeTestEntity;
import com.demo.modules.ge.manager.GeTestManager;
import com.demo.modules.ge.service.GeTestService;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
@Service("geTestService")
public class GeTestServiceImpl implements GeTestService {

	@Autowired
	private GeTestManager geTestManager;

    @Override
    public int add(GeTestEntity geTest) {
        return geTestManager.add(geTest);
    }

    @Override
    public int removeLogicalById(Long id) {
        return geTestManager.removeLogicalById(id);
    }

    @Override
    public int removeLogicalById(List<Long> ids) {
        return geTestManager.removeLogicalById(ids);
    }

    @Override
    public int removePhysicalById(Long id) {
        return geTestManager.removePhysicalById(id);
    }

    @Override
    public int removePhysicalById(List<Long> ids) {
        return geTestManager.removePhysicalById(ids);
    }

    @Override
    public int removeAll() {
        return geTestManager.removeAll();
    }

    @Override
    public int update(GeTestEntity geTest) {
        return geTestManager.update(geTest);
    }

    @Override
    public GeTestEntity getById(Long id) {
        return geTestManager.getById(id);
    }

    @Override
    public GeTestEntity getByObject(GeTestEntity geTest) {
        return geTestManager.getByObject(geTest);
    }

    @Override
    public Page<GeTestEntity> list(GeTestEntity geTest, Page<GeTestEntity> page) {
		geTestManager.list(geTest, page);
        return page;
    }

    @Override
    public List<GeTestEntity> list(GeTestEntity geTest) {
        return geTestManager.list(geTest);
    }

    @Override
    public List<GeTestEntity> listAll() {
        return geTestManager.listAll();
    }

    @Override
    public Page<GeTestEntity> listGeTest(Map<String, Object> params) {
        Query query = new Query(params);
        Page<GeTestEntity> page = new Page<>(query);
            geTestManager.listGeTest(page, query);
        return page;
    }

}
