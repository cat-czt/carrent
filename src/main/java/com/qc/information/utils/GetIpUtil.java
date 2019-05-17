package com.qc.information.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * @Author: czt
 * @Date: 18-12-29 下午4:36
 */
public class GetIpUtil {
    /**
     * 根据网卡获取ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.ie10-viewport-bug-workaround.js") || ipAddress.equals("0:0:0:0:0:0:0:ie10-viewport-bug-workaround.js")) {
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }
        if (ipAddress.equals("::ie10-viewport-bug-workaround.js")) {
            //根据网卡获取本机配置的IP地址
            ipAddress = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if (null != ipAddress && ipAddress.length() > 15) {
            //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 根据url获取主机ip
     * @return
     * @param url
     */
    public static String getUrlIp(String url){
        InetAddress address = null;
        try {
            address = InetAddress.getByName(url);
        }
        catch (UnknownHostException e) {
            System.exit(2);
        }
       return address.getHostAddress();
    }

    /**
     * 获取指定地址的域名 ip端口信息
     * @param uri
     * @return
     */
    public static String getIP(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }

        return effectiveURI.toString();
    }
}
