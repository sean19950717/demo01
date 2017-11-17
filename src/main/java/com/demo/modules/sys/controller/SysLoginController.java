package com.demo.modules.sys.controller;

import com.demo.common.annotation.SysLog;
import com.demo.common.entity.R;
import com.demo.common.utils.MD5Utils;
import com.demo.common.utils.RetryLimitUtils;
import com.demo.common.utils.ShiroUtils;
import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 用户controller
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月8日 下午2:48:50
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RetryLimitUtils retryLimitUtils;
	@Value(value = "${Login.retryLimitCounts}")
	private Integer retryCount;
	/**
	 * 登录
	 */
	@SysLog(module = "登录模块",value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password)throws IOException {
		SysUserEntity user = sysUserService.getByUserName(username);
		password = MD5Utils.encrypt(username, password);
		if (retryLimitUtils.checkLockStatus(username)){
			return R.error("失败次数过多，已被锁定，请稍后重试");
		}
		
		if(user == null || !user.getPassword().equals(password)) {
			int time=retryLimitUtils.addLimitCount(username);
			int leftTime=retryCount-time;
			return R.error("用户名或密码错误,您还有"+leftTime+"次机会");
		}
		
		if(user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}
	    
		return sysUserService.saveUserToken(user);
	}
	
	/**
	 * 退出
	 */
	@SysLog(module = "登录模块",value ="退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public R logout() {
		R r = sysUserService.updateUserToken(getUserId());
		ShiroUtils.logout();
		return r;
	}
	
}
