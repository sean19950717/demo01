package com.demo.modules.generator.manager.impl;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.generator.dao.SysGeneratorMapper;
import com.demo.modules.generator.entity.ColumnEntity;
import com.demo.modules.generator.entity.TableEntity;
import com.demo.modules.generator.manager.SysGeneratorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:54:44
 */
@Component("sysGeneratorManager")
public class SysGeneratorManagerImpl implements SysGeneratorManager {

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;
	
	@Override
	public void listTable(Page<TableEntity> page, Query query) {
		sysGeneratorMapper.listTable(page, query);
	}

	@Override
	public TableEntity getTableByName(String tableName) {
		return sysGeneratorMapper.getTableByName(tableName);
	}

	@Override
	public List<ColumnEntity> listColumn(String tableName) {
		return sysGeneratorMapper.listColumn(tableName);
	}

}
