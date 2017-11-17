package com.demo.modules.oss.service;


import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 * 
 * @author Centling Technologies
 * @email xxx@demo.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService {


	Page<SysOssEntity> listForPage(Map<String,Object>param);

	int saveFileUpload(SysOssEntity ossEntity);

	public R deleteById(int[] ids);
}
