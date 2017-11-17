package com.demo.modules.generator.dao;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.generator.entity.ColumnEntity;
import com.demo.modules.generator.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 代码生成器
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 下午8:47:12
 */
@Mapper
public interface SysGeneratorMapper {

	List<TableEntity> listTable(Page<TableEntity> page, Query query);
	
	TableEntity getTableByName(String tableName);
	
	List<ColumnEntity> listColumn(String tableName);
	
}
