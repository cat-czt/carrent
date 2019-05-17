package com.qc.information.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author: czt
 * @Date: 18-10-31 上午9:36
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String post(String url, Map<String, String> map) {
        return send(url, map, false);
    }

    public static String postHttps(String url, Map<String, String> map) {
        return send(url, map, true);
    }

    private static String send(String url, Map<String, String> map, boolean isHttps) {
        CloseableHttpClient client = null;
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            if (isHttps) {
                SSLConnectionSocketFactory sslsf = buildSSLContext();
                client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } else {
                client = HttpClients.createDefault();
            }
            HttpPost method = new HttpPost(url);
            if (map != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : map.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            logger.error("---> connect error_" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                logger.error("---> IO Exception error_" + e.getMessage());
                e.printStackTrace();
            }
        }
        return responseText;
    }

    public static String getHttps(String url) throws Exception {
        return getData(url, true);
    }

    public static String get(String url) throws Exception {
        return getData(url, false);
    }

    private static String getData(String url, boolean isHttps) {
        String rs = "";
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            if (isHttps) {
                SSLConnectionSocketFactory sslsf = buildSSLContext();
                client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } else {
                client = HttpClients.createDefault();
            }
            HttpGet httpGet = new HttpGet(url);
            //  设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000).setConnectTimeout(5000).build();
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("Content-Type", "text/xml;charset=UTF-8");
            response = client.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                rs = EntityUtils.toString(httpEntity, "UTF-8");
            }

        } catch (Exception e) {
            logger.error("---> connect error_" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                logger.error("---> IO Exception error:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return rs;
    }

    private static SSLConnectionSocketFactory buildSSLContext()
            throws Exception {
        SSLContext sslcontext = SSLContexts.custom().setSecureRandom(new SecureRandom()).loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return sslsf;
    }

    private static String resolveResult(Response response) {
        if (response.getStatus() != 200 && response.getStatus() != 204 && response.getStatus() != 205) {
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
        String rss = response.readEntity(String.class);
        return rss;
    }

    /**
     * 获取请求设备类型
     *
     * @param request
     * @return String
     */
    public static String getRequestUser(HttpServletRequest request) {
        String rs = "pc";
        String requestHeader = request.getHeader("user-agent");
        String[] deviceArray = new String[]{"android", "mac os", "windows phone"};
        if (!StringUtil.isNullOrEmpty(requestHeader)) {
            requestHeader = requestHeader.toLowerCase();

            for (int i = 0; i < deviceArray.length; i++) {
                if (requestHeader.indexOf(deviceArray[i]) > 0) {
                    return deviceArray[i];
                }
            }
        }
        return rs;
    }

    /**
     * 获取请求对象ip地址
     *
     * @param request
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    ip = ip.substring(0, index);
                }
            } else {
                ip = request.getRemoteAddr();
            }
        }
        if (StringUtil.isNullOrEmpty(ip)) {
            ip = "";
        }
        return ip;
    }

}

