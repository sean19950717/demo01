package com.demo.modules.ge.service;

import java.util.List;
import java.util.Map;
import com.demo.common.entity.Page;
import com.demo.modules.ge.entity.GeTestEntity;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
public interface GeTestService {

    int add(GeTestEntity geTest);

    int removeLogicalById(Long id);
    int removeLogicalById(List<Long> ids);
    int removePhysicalById(Long id);
    int removePhysicalById(List<Long> ids);
    int removeAll();

    int update(GeTestEntity geTest);

	GeTestEntity getById(Long id);
	GeTestEntity getByObject(GeTestEntity geTest);

    Page<GeTestEntity> list(GeTestEntity geTest,Page<GeTestEntity> page);
    List<GeTestEntity> list(GeTestEntity geTest);
    List<GeTestEntity> listAll();

    Page<GeTestEntity> listGeTest(Map<String, Object> params);
}
