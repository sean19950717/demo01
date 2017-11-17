package com.demo.modules.generator.service.impl;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.generator.entity.ColumnEntity;
import com.demo.modules.generator.entity.GeneratorParamEntity;
import com.demo.modules.generator.entity.TableEntity;
import com.demo.modules.generator.manager.SysGeneratorManager;
import com.demo.modules.generator.service.SysGeneratorService;
import com.demo.modules.generator.utils.GenUtils;
import com.demo.modules.sys.dao.SysMenuMapper;
import com.demo.modules.sys.entity.SysMenuEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:55:51
 */
@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {
    @Value("${generator.basePackage}")
    private String basePackage;
    @Value("${generator.mapper.path}")
    private String mapperPath;
    @Value("${generator.view.path}")
    private String viewPath;
    @Value("${generator.js.path}")
    private String jsPath;
	@Autowired
	private SysGeneratorManager sysGeneratorManager;

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public Page<TableEntity> listTable(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TableEntity> page = new Page<>(query);
		sysGeneratorManager.listTable(page, query);
		return page;
	}

	@Override
	public byte[] generator(GeneratorParamEntity params) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(out);
		params.setBasePackage(basePackage);
		params.setJsPath(jsPath);
		params.setMapperPath(mapperPath);
        params.setViewPathGenerator(viewPath);
		for(String table : params.getTables()) {
			TableEntity tableEntity = sysGeneratorManager.getTableByName(table);
			List<ColumnEntity> columns = sysGeneratorManager.listColumn(table);
			GenUtils.generatorCode(tableEntity, columns, params, zip);
		}
		//TODO:插入对应的菜单、权限数据
		SysMenuEntity sysMenuEntity = new SysMenuEntity();
		sysMenuMapper.save(sysMenuEntity);
		IOUtils.closeQuietly(zip);
		return out.toByteArray();
	}
}
