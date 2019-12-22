package com.taotao.service.impl.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created on 2019/12/22
 *
 * @author Tu ShengTao
 * Description:
 */
public class HttpUtils {


        /**
         * 发送 get请求
         */
        public static String get(String param,String URL) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String obj=null;
            try {
                HttpUriRequest httpget = new HttpGet(URL+"?"+param);
                // 执行get请求.
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    // 打印响应状态
                    System.out.println(response.getStatusLine());
                    if (entity != null) {
                        obj= EntityUtils.toString(entity);
                    }
                } finally {
                    response.close();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭连接,释放资源
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return obj;
        }


        /**
         * 发送 post请求
         */
        public static String post(String json,String URL) {
            String obj=null;
            // 创建默认的httpClient实例.
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建httppost
            HttpPost httppost = new HttpPost(URL);
            httppost.addHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Accept", "application/json");
            try {
                StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));  //对参数进行编码，防止中文乱码
                s.setContentEncoding("UTF-8");
                httppost.setEntity(s);
                CloseableHttpResponse response = httpclient.execute(httppost);
                try {
                    //获取相应实体
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        obj=EntityUtils.toString(entity, "UTF-8");
                    }

                } finally {
                    response.close();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭连接,释放资源
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return obj;
        }

}
