package com.demo.modules.ge.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.ge.dao.GeTestMapper;
import com.demo.modules.ge.entity.GeTestEntity;
import com.demo.modules.ge.manager.GeTestManager;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
@Component("geTestManager")
public class GeTestManagerImpl implements GeTestManager {

	@Autowired
	private GeTestMapper geTestMapper;


    @Override
    public int add(GeTestEntity geTest) {
        return geTestMapper.insertSelective(geTest);
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
        return geTestMapper.deleteById(id);
    }

    @Override
    public int removePhysicalById(List<Long> ids) {
        return geTestMapper.deleteByIds(ids);
    }

    @Override
    public int removeAll() {
        return geTestMapper.deleteAll();
    }

    @Override
    public int update(GeTestEntity geTest) {
        return geTestMapper.updateByIdSelective(geTest);
    }

    @Override
    public GeTestEntity getById(Long id) {
        return geTestMapper.selectOneById(id);
    }

    @Override
    public GeTestEntity getByObject(GeTestEntity geTest) {
        return geTestMapper.selectOneByObject(geTest);
    }

    @Override
    public List<GeTestEntity> list(GeTestEntity geTest, Page<GeTestEntity> page) {
        return geTestMapper.selectByObject(geTest,page);
    }

    @Override
    public List<GeTestEntity> list(GeTestEntity geTest) {
        return geTestMapper.selectByObject(geTest,null);
    }

    @Override
    public List<GeTestEntity> listAll() {
        return geTestMapper.selectAll();
    }

    @Override
    public List<GeTestEntity> listGeTest(Page<GeTestEntity> page, Query search) {
        return geTestMapper.listForPage(page, search);
    }
}
