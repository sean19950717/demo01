package com.demo.modules.sys.controller;

import com.aliyuncs.exceptions.ClientException;
import com.demo.common.annotation.SysLog;
import com.demo.common.entity.R;
import com.demo.common.utils.*;
import com.demo.modules.sys.entity.SmsCaptcha;

import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.service.SmsCaptchaService;
import com.demo.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import java.util.Map;
import java.util.UUID;

import com.google.code.kaptcha.Producer;

import static com.demo.common.utils.CommonUtils.isNullOrEmpty;

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
	@Autowired
	private Producer producer;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private SmsCaptchaService smsCaptchaService;
	@Autowired
	private MsgUtils msgUtils;
	private static String CAPATCHAPICTURE="CAPATCHA_PICTURE";

	/**
	 *
	 * 验证码
	 */
	@RequestMapping(value = "/captcha")
	public void captcha(HttpServletResponse response, HttpServletRequest request,String captcha )
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		String CapatchaCode=getKapatchCode();
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@SysLog(module = "登录模块",value = "登录")
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password,String captcha)throws IOException {
		String Kaptcha=redisUtils.get(CAPATCHAPICTURE);
		if (!Kaptcha.equals(captcha)){
			return R.error("验证码错误");
		}
		SysUserEntity user = sysUserService.getByUserName(username);
		password = MD5Utils.encrypt(username, password);

		if(isNullOrEmpty(user)){
			return  R.error("用户名不存在");
		}
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
	 * @param telephone
	 * 注册账号
	 */
	@SysLog(module = "登录模块",value = "注册")
	@RequestMapping(value = "/smsInfo", method = RequestMethod.GET)
	public R smsVerification(@RequestParam String telephone){
		try {
			msgUtils.sendMsg(telephone);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		SmsCaptcha smsCatcha=new SmsCaptcha();
		Date date=new Date();
		String smsCaptchaInfo=redisUtils.get(telephone);
		String currentTime=DateUtils.format(date,DateUtils.DATE_TIME_PATTERN);
		smsCatcha.setCreateTime(currentTime);
		smsCatcha.setSmsTelephone(telephone);
		smsCatcha.setSmsCapachaInfo(smsCaptchaInfo);
		return smsCaptchaService.save(smsCatcha);
	}

	/**
	 *
	 * @param telephone
	 * @return
	 */
	@SysLog(module = "登录模块",value = "注册")
	@RequestMapping("/register")
	public R register(@RequestParam String telephone,String password,String captcha){
		String smsCatchaInfo=redisUtils.get(telephone);
		if (smsCatchaInfo==null){
			return R.error("验证码已超时，请重新获取");
		}
		if (!captcha.equals(smsCatchaInfo)){
			R.error("验证码输入错误");
		}
		if (sysUserService.getObjectByTelephone(telephone)>0){
			R.error("手机号已经被注册");
		}
		SysUserEntity sysUserEntity=new SysUserEntity();
		sysUserEntity.setMobile(telephone);
		sysUserEntity.setUsername(telephone);
		sysUserEntity.setPassword(password);
		redisUtils.delete(telephone);
		return sysUserService.saveUser(sysUserEntity);
	}
	@SysLog(module = "登录模块",value = "修改密码")
	@RequestMapping("/modifyPassWord")
	public R modifyPassword(@RequestBody String telephone,String smsCaptcha,String password){
		String smsCatchaInfo=redisUtils.get(telephone);
		if (smsCatchaInfo==null){
			return R.error("验证码已超时，请重新获取");
		}
		if (!smsCaptcha.equals(smsCatchaInfo)){
			R.error("验证码输入错误");
		}
		SysUserEntity sysUserEntity=new SysUserEntity();
		sysUserEntity.setMobile(password);
		sysUserEntity.setMobile(telephone);
		redisUtils.delete(telephone);

		return sysUserService.updatePswd(sysUserEntity);
	}
	/**
	 * 退出
	 */
	@SysLog(module = "登录模块",value ="退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public R logout() {
		ShiroUtils.logout();
		return R.ok("退出成功");
	}
 	public String getKapatchCode(){
		UUID uuid=UUID.randomUUID();
		String kapatchaCode=uuid.toString().replaceAll("-","");
		return kapatchaCode;
	}
	@RequestMapping(value = "/CaptchaCode")
	public R captcha(String captchaText){
 		String CapatchaCode=getKapatchCode();
		//redisUtils.set(CapatchaCode,captchaText,RedisUtils.CAPACHA_EXPIRE);
		return  R.ok(CapatchaCode);
	}
}
