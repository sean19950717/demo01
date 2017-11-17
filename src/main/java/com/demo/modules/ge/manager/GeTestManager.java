package com.demo.modules.ge.manager;

import java.util.List;
import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.ge.entity.GeTestEntity;

/**
 * 
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年11月08日 下午3:39:21
 */
public interface GeTestManager {

    int add(GeTestEntity geTest);

    int removeLogicalById(Long id);
    int removeLogicalById(List<Long> ids);
    int removePhysicalById(Long id);
    int removePhysicalById(List<Long> ids);
    int removeAll();

    int update(GeTestEntity geTest);

	GeTestEntity getById(Long id);
	GeTestEntity getByObject(GeTestEntity geTest);

    List<GeTestEntity> list(GeTestEntity geTest,Page<GeTestEntity> page);
    List<GeTestEntity> list(GeTestEntity geTest);
    List<GeTestEntity> listAll();

    List<GeTestEntity> listGeTest(Page<GeTestEntity> page, Query search);
}
