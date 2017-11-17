package com.demo.common.aspect;

import com.demo.common.annotation.SysLog;
import com.demo.common.utils.*;
import com.demo.modules.sys.entity.SysLogEntity;
import com.demo.modules.sys.entity.SysUserEntity;
import com.demo.modules.sys.manager.SysLogManager;
import com.google.common.base.Optional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类
 * 
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月28日 上午11:07:35
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogManager sysLogManager;
	
	@Pointcut("@annotation(com.demo.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = point.proceed();
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - beginTime;
		this.saveSysLog(point, executeTime);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long executeTime) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog sysLogAnnotation = method.getAnnotation(SysLog.class);

		String module = sysLogAnnotation.module();
		String operation = sysLogAnnotation.value();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		Object param = args.length == 0 ? "" : args[0];
		String params = JSONUtils.beanToJson(param);
		String ip = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
		Long currentUserId = -1L;
		String currentUserName = "系统";
		Optional<SysUserEntity> currUserOptional = ShiroUtils.getCurrentUser();
		if(currUserOptional.isPresent()){
			SysUserEntity currentUser = currUserOptional.get();
			currentUserId = currentUser.getUserId();
			currentUserName = currentUser.getUsername();
		}

		SysLogEntity operationLog = new SysLogEntity();
		operationLog.setModule(module);
		operationLog.setOperation(operation);
		operationLog.setMethod(className + "." + methodName + "()");
		operationLog.setParams(params);
		operationLog.setIp(ip);
		operationLog.setExecuteTime(executeTime);
		operationLog.setUserId(currentUserId);
		operationLog.setUsername(currentUserName);

		sysLogManager.saveLog(operationLog);
	}
	
}
