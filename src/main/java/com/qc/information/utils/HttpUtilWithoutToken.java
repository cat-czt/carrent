package com.qc.information.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtilWithoutToken {
    private static String ENCODING = "UTF-8";

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
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


    public static String get(String url) throws Exception {
        String rs = "";
        // 创建HttpClientBuilder
        SSLConnectionSocketFactory sslsf = buildSSLContext();
        CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpGet httpGet = new HttpGet(url);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(20000).setConnectTimeout( 20000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Content-Type", "text/xml;charset=UTF-8");
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            rs = EntityUtils.toString(httpEntity, "UTF-8");
        }
        // 释放资源
        closeableHttpClient.close();
        return rs;

    }
}
