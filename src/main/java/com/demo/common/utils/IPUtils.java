package com.demo.common.utils;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月8日 下午12:02:56
 */
public class IPUtils {

	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
	public final static String UNKNOWN = "unKnown";

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	private static boolean isKnown(String xfor){
		return !isUnKnown(xfor);
	}

	private static boolean isUnKnown(String xfor) {
		return UNKNOWN.equalsIgnoreCase(xfor);
	}

	public static String getIpAddr(HttpServletRequest request) {

		String xip = request.getHeader("X-Real-IP");
		String xfor = request.getHeader("X-Forwarded-For");

		boolean useReverseProxy = !Strings.isNullOrEmpty(xfor) && isKnown(xfor);
		if(useReverseProxy){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = xfor.indexOf(",");
			boolean hasMoreIp = index != -1;
			if(hasMoreIp){
				return xfor.substring(0,index);
			}else{
				return xfor;
			}
		}

		if(!Strings.isNullOrEmpty(xip) && isKnown(xip)){
			return xip;
		}

		if (Strings.isNullOrEmpty(xip) && isKnown(xip)) {
			xip = request.getHeader("Proxy-Client-IP");
		}

		if (Strings.isNullOrEmpty(xip) && isKnown(xip)) {
			xip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (Strings.isNullOrEmpty(xip) && isKnown(xip)) {
			xip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (Strings.isNullOrEmpty(xip) && isKnown(xip)) {
			xip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (Strings.isNullOrEmpty(xip) && isKnown(xip)) {
			xip = request.getRemoteAddr();

			if("127.0.0.1".equals(xip) || "0:0:0:0:0:0:0:1".equals(xip)) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				xip= inet.getHostAddress();
			}
		}

		return xip;
	}
}
