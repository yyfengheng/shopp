package com.caihongjia.httpclient;

import java.io.IOException;
import java.net.http.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caihongjia.common.constant.HttpClientConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/address")
@Slf4j
public class AddressServer {
    
    /**
     * 第三方接口: 通过关键字查询地址
     * @param keyword
     */
    @GetMapping("/getAddressDetails")
    public String getAddressDetails(String keyword){
       log.info("通过关键字查询地址:{}",keyword);

        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求对象
        HttpGet httpGet = new HttpGet("https://apis.map.qq.com/ws/place/v1/suggestion?key="+HttpClientConstant.ADDRESS_KEY + "&keyword=" + keyword);
        String body = "";
        try {
            // 发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 获取服务端返回的数据
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
            System.out.println(body);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return body;

    }
}
