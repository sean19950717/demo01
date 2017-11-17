package com.demo.modules.sys.service;

import com.demo.common.entity.R;
import com.demo.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月10日 上午10:35:58
 */
public interface SysMenuService {
	
	R listUserMenu(Long userId);
	
	List<SysMenuEntity> listMenu(Map<String, Object> params);
	
	R listNotButton();
	
	R saveMenu(SysMenuEntity menu);

	R getMenuById(Long id);
	
	R updateMenu(SysMenuEntity menu);
	
	R batchRemove(Long[] id);

}
