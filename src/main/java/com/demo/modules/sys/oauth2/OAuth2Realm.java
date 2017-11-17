package com.demo.modules.sys.oauth2;

import com.alibaba.fastjson.JSON;
import com.demo.common.utils.JwtUtils;
import com.demo.common.utils.RedisKeys;
import com.demo.common.utils.RedisUtils;
import com.demo.common.utils.ShiroUtils;
import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.entity.SysUserTokenEntity;
import com.demo.modules.sys.manager.SysUserManager;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 认证
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年9月3日 上午3:24:29
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
	
	@Autowired
	private SysUserManager sysUserManager;
    @Value(value = "${spring.redis.open}")
    private boolean redisOpenStatus;
    @Value(value = "${Login.singleLogin}")
    private  boolean singleLoginStstus;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	Long userId = ShiroUtils.getUserId();
		//用户角色
		Set<String> rolesSet = sysUserManager.listUserRoles(userId);
		//用户权限
		Set<String> permsSet = sysUserManager.listUserPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(rolesSet);
		info.setStringPermissions(permsSet);
		return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        SysUserEntity sysUserEntity=null;
        try{
            Claims claims= JwtUtils.parseJWT(accessToken);
            String subject=claims.getSubject();
            sysUserEntity= JSON.parseObject(subject,SysUserEntity.class);
        }catch (Exception e){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        if (sysUserEntity==null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        if (singleLoginStstus){
            //根据accessToken，查询用户信息
            if (redisOpenStatus){
                String redisToken=redisUtils.get(RedisKeys.getUserWebTokenKey(sysUserEntity.getUserId()+""));
                if(redisToken==null || !redisToken.equals(accessToken) ){
                    throw new IncorrectCredentialsException("token失效，请重新登录");
                }
            }else {
                SysUserTokenEntity tokenEntity = sysUserManager.getByToken(accessToken);
                //token失效
                if(tokenEntity == null){
                    throw new IncorrectCredentialsException("token失效，请重新登录");
                }
            }

        }
        //查询用户信息
        SysUserEntity user = sysUserManager.getById(sysUserEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
