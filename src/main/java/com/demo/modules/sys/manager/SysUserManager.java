package com.demo.modules.sys.manager;

import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.entity.SysUserTokenEntity;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月11日 上午11:43:01
 */
public interface SysUserManager {

	SysUserEntity getByUserName(String username);
	
	List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search);
	
	int saveUser(SysUserEntity user);
	
	SysUserEntity getById(Long userId);
	
	int updateUser(SysUserEntity user);
	
	int batchRemove(Long[] id);
	
	Set<String> listUserPerms(Long userId);
	
	Set<String> listUserRoles(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserEnable(Long[] id);
	
	int updateUserDisable(Long[] id);
	
	int updatePswd(SysUserEntity user);
	
	SysUserEntity getUserById(Long userId);
	
	SysUserTokenEntity getByToken(String token);
	
	SysUserTokenEntity saveUserToken(Long userId);

	SysUserTokenEntity saveUserToken(SysUserEntity sysUserEntity);
	
	int updateUserToken(Long userId);

	int getObjectByTelephone(String telephone);
	
}
