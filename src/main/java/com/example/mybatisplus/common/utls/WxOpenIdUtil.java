package com.example.mybatisplus.common.utls;

/**
 * @author gxy
 * @date 2022/3/1$
 */

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WxOpenIdUtil {
    public static String getOpenId(String code) {
        String appid = "wx41181eecebbdd4b8";
        String secret = "315939f2fc23bfc8be454ca06a9425ed";

        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        BufferedReader in = null;

        try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            String openid=jsonObject.getString("openid");
            System.out.println(openid);
            return openid;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}


