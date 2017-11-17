package com.demo.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年9月3日 下午8:03:17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(new HashMap<Object,Object>(targetDataSources));
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}

	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}

}
