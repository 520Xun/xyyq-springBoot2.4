package com.xun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
	private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);
	
	
	public static String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String ip = null;
			try {
				ip = request.getHeader("x-forwarded-for");
				if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
				}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_CLIENT_IP");
				}
				if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				}
				if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
			} catch (Exception e) {
				logger.error("IPUtils ERROR ", e);
			}
			return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		}

}
