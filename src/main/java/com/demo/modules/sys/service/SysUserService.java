package com.demo.modules.sys.service;

import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 * 系统用户
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月11日 上午11:45:42
 */
public interface SysUserService {

	Page<SysUserEntity> listUser(Map<String, Object> params);
	
	R saveUser(SysUserEntity user);
	
	R getUserById(Long userId);
	
	R updateUser(SysUserEntity user);
	
	R batchRemove(Long[] id);
	
	R listUserPerms(Long userId);
	
	R updatePswdByUser(SysUserEntity user);
	
	R updateUserEnable(Long[] id);
	
	R updateUserDisable(Long[] id);
	
	R updatePswd(SysUserEntity user);
	
	R saveUserToken(Long userId);

	R saveUserToken(SysUserEntity user);
	
	R updateUserToken(Long userId);
	
	SysUserEntity getByUserName(String username);

	int getObjectByTelephone(String telephone);
	
}
