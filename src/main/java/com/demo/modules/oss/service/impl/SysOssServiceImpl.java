package com.demo.modules.oss.service.impl;


import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.common.entity.R;
import com.demo.common.utils.CommonUtils;
import com.demo.modules.oss.dao.SysOssDao;
import com.demo.modules.oss.entity.SysOssEntity;
import com.demo.modules.oss.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {
	@Autowired
	private SysOssDao sysOssDao;


	@Override
	public Page<SysOssEntity> listForPage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysOssEntity> page = new Page<>(query);
		sysOssDao.listForPage(page,query);
		return page;
	}

	@Override
	public int saveFileUpload(SysOssEntity ossEntity) {
		return sysOssDao.save(ossEntity);
	}

	@Override
	public R deleteById(int[] ids) {
		int count=sysOssDao.deleteById(ids);
		return CommonUtils.msg(count);
	}
}
