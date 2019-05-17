package com.qc.information.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: czt
 * @Date: 18-11-2 上午9:36
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * http get请求
     *
     * @param token 请求头所需验证token
     * @param url   请求url
     * @param param 请求参数
     * @return
     */
    public static String get(String token, String url, Map<String, String> param) {
        return send(token, url, param, "get");
    }

    /**
     * http post请求
     *
     * @param token 请求头所需验证token
     * @param url   请求url
     * @param param 请求参数
     * @return
     */
    public static String post(String token, String url, Map<String, String> param) {
        return send(token, url, param, "post");
    }

    /**
     * @param token 请求头所需验证token
     * @param url   请求url
     * @param map   请求参数
     * @param type  请求类型，目前只有get和post
     * @return
     */
    private static String send(String token, String url, Map<String, String> map, String type) {
        CloseableHttpClient client = null;
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            //get请求
            if ("get".equals(type)) {
                //设置参数
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                if (!ObjectUtils.isEmpty(map)) {
                    for (Map.Entry<String, String> param : map.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                        params.add(pair);
                    }
                }
                //设置请求头 header
                String str;
                str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
                HttpGet httpGet = new HttpGet(url + "?" + str);
                httpGet.setHeader("token", token);
                httpGet.setHeader("Content-Type", "application/json");
                client.execute(httpGet);
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity, "UTF-8");
                }
            } else if ("post".equals(type)) {
                HttpPost httpPost = new HttpPost(url);
                //设置请求头
                httpPost.setHeader("token", token);
                httpPost.setHeader("Content-Type", "application/json");
                //设置参数
                if (!ObjectUtils.isEmpty(map)) {
                    JSONObject params = new JSONObject();
                    for (Map.Entry<String, String> param : map.entrySet()) {
                        params.put(param.getKey(), param.getValue());
                    }
                    StringEntity stringEntity;
                    stringEntity = new StringEntity(params.toJSONString(), "utf-8");
                    httpPost.setEntity(stringEntity);
                }
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity, "UTF-8");
                }
            } else {
                logger.error("----> error request type Exception_请求类型错误！");
                throw new Exception("请求类型错误！");
            }
        } catch (Exception e) {
            logger.error("----> error request Exception_" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                logger.error("---->IO Exception_" + e.getMessage());
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /**
     * @param token 请求头所需验证token
     * @param url   请求url
     * @param param 请求参数,加密后的
     * @return
     */
    public static Object httpGet(String token, String url, String param) {
        CloseableHttpClient client = null;
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url + "?" + param);
            httpGet.setHeader("token", token);
            httpGet.setHeader("Content-Type", "application/json");
            client.execute(httpGet);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----> connect Exception_" + e.getMessage());
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                logger.error("----> IO Exception_" + e.getMessage());
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /**
     * 请求返回结果
     *
     * @param token
     * @param url
     * @param param
     * @param isGet
     * @return
     */
    public static Object getResponseData(String token, String url, Map param, boolean isGet) {
        try {
            String result = "";
            if (isGet) {
                result = get(token, url, param);
            } else {
                result = post(token, url, param);
            }
            return result;
        } catch (Exception e) {
            logger.error(new String("请求" + url + "异常{}"), e.getMessage());
            return new JSONObject();
        }
    }

    /**
     * 判断地址是否能正常访问
     * @param address
     * @return
     * @throws Exception
     */
    public static boolean getStatus(String address){
        try {
            URL urlObj = new URL(address);
            HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
            oc.setUseCaches(false);
            oc.setConnectTimeout(3000); // 设置超时时间 5s
            int status = oc.getResponseCode();// 请求状态
            if (200 == status) {
                // 200是请求地址顺利连通。。
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error request",e);
        }
        return false;
    }
}
