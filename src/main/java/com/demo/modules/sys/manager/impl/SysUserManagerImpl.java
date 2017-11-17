package com.demo.modules.sys.manager.impl;

import com.alibaba.fastjson.JSON;
import com.demo.common.constant.JwtConstant;
import com.demo.common.constant.SystemConstant;
import com.demo.common.entity.Page;
import com.demo.common.entity.Query;
import com.demo.common.utils.JwtUtils;
import com.demo.common.utils.RedisKeys;
import com.demo.common.utils.RedisUtils;
import com.demo.modules.sys.dao.*;
import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.entity.SysUserTokenEntity;
import com.demo.modules.sys.manager.SysUserManager;
import com.demo.modules.sys.oauth2.TokenGenerator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 系统用户
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月11日 上午11:44:21
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {
	private Logger logger= LoggerFactory.getLogger(SysUserManagerImpl.class);
	@Autowired
	private RedisUtils redisUtils;
	/**
	 * token过期时间，12小时
	 */
	private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysUserTokenMapper sysUserTokenMapper;

	@Value(value = "${spring.redis.open}")
	private boolean redisOpenStatus;
	@Value(value = "${Login.singleLogin}")
	private  boolean singleLoginStstus;
	
	@Override
	public List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search) {
		return sysUserMapper.listForPage(page, search);
	}

	@Override
	public SysUserEntity getByUserName(String username) {
		return sysUserMapper.getByUserName(username);
	}

	@Override
	public int saveUser(SysUserEntity user) {
		int count = sysUserMapper.save(user);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return count;
	}

	@Override
	public SysUserEntity getById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
		return user;
	}

	@Override
	public int updateUser(SysUserEntity user) {
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return count;
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return count;
	}

	@Override
	public Set<String> listUserPerms(Long userId) {
		List<String> perms = sysMenuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for(String perm : perms) {
			if(StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public Set<String> listUserRoles(Long userId) {
		List<String> roles = sysRoleMapper.listUserRoles(userId);
		Set<String> rolesSet = new HashSet<>();
		for(String role : roles) {
			if(StringUtils.isNotBlank(role)) {
				rolesSet.addAll(Arrays.asList(role.trim().split(",")));
			}
		}
		return rolesSet;
	}

	@Override
	public int updatePswdByUser(Query query) {
		return sysUserMapper.updatePswdByUser(query);
	}

	@Override
	public int updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updatePswd(SysUserEntity user) {
		return sysUserMapper.updatePswd(user);
	}

	@Override
	public SysUserEntity getUserById(Long userId) {//不包含角色信息
		return sysUserMapper.getObjectById(userId);
	}

	@Override
	public SysUserTokenEntity getByToken(String token) {
		return sysUserTokenMapper.getByToken(token);
	}

	@Override
	public SysUserTokenEntity saveUserToken(Long userId) {
		//生成token
		String token = TokenGenerator.generateValue();
		//当前时间
		Date now = new Date();
		Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
		SysUserTokenEntity userToken = sysUserTokenMapper.getByUserId(userId);
		if(userToken == null) {
			userToken = new SysUserTokenEntity();
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			sysUserTokenMapper.save(userToken);
		} else {
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			sysUserTokenMapper.update(userToken);
		}
		return userToken;
	}

	@Override
	public SysUserTokenEntity saveUserToken(SysUserEntity sysUserEntity) {
		//生成token
		String jwtToken="";
		try{
			jwtToken= JwtUtils.createJwtWebToken(sysUserEntity.getUserId()+"", JSON.toJSONString(sysUserEntity));
		}catch (Exception e){
			logger.info("生成token失败{}",e.getMessage());
		}
		Date currentDate=new Date();
		Date gmtExpire = new Date(System.currentTimeMillis() + JwtConstant.JWT_WEB_EXPIRETIME);
		SysUserTokenEntity userToken = new SysUserTokenEntity();
		userToken.setUserId(sysUserEntity.getUserId());
		userToken.setToken(jwtToken);
		userToken.setGmtExpire(gmtExpire);
		userToken.setGmtModified(currentDate);
		if (singleLoginStstus ){
			logger.info("系统设置为单点登录");
			//如果redis配置为开启状态，那么将token存储到redis中
			if(redisOpenStatus){
				logger.info("redis开启，将用户登录token存储在redis中");
				String redisKey= RedisKeys.getUserWebTokenKey(sysUserEntity.getUserId()+"");
				redisUtils.set(redisKey,jwtToken);
			}else{
				userToken = sysUserTokenMapper.getByUserId(sysUserEntity.getUserId());
				if(userToken == null) {
					userToken = new SysUserTokenEntity();
					userToken.setUserId(sysUserEntity.getUserId());
					userToken.setToken(jwtToken);
					userToken.setGmtExpire(gmtExpire);
					userToken.setGmtModified(currentDate);
					sysUserTokenMapper.save(userToken);
				} else {
					userToken.setToken(jwtToken);
					userToken.setGmtExpire(gmtExpire);
					userToken.setGmtModified(currentDate);
					sysUserTokenMapper.update(userToken);
				}
			}
		}
		return userToken;
	}

	@Override
	public int updateUserToken(Long userId) {
		String token = TokenGenerator.generateValue();
		SysUserTokenEntity userToken = new SysUserTokenEntity();
		userToken.setUserId(userId);
		userToken.setToken(token);
		return sysUserTokenMapper.update(userToken);
	}

}
