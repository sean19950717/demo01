package com.demo.modules.generator.manager;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.generator.entity.ColumnEntity;
import com.demo.modules.generator.entity.TableEntity;

import java.util.List;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:54:09
 */
public interface SysGeneratorManager {

	void listTable(Page<TableEntity> page, Query query);
	
	TableEntity getTableByName(String tableName);
	
	List<ColumnEntity> listColumn(String tableName);
	
}
