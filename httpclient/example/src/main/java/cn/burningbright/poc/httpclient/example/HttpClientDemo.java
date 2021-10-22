package cn.burningbright.poc.httpclient.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author burningbright
 * @since 2021/10/20 17:32 PM
 */
@Slf4j
public class HttpClientDemo {

    private static final CloseableHttpClient httpClient;

    static {

        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .setSoKeepAlive(true)
                .setSoReuseAddress(true)
                .setSoTimeout(3000).build();

        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(2000)
            .setConnectTimeout(5000)
            .setSocketTimeout(5000)
            .build();

        httpClient = HttpClientBuilder
            .create()
            .setDefaultSocketConfig(socketConfig)
            .setDefaultRequestConfig(requestConfig)
            .setMaxConnTotal(1)
            .build();
    }
    
    public static void postDemo(int index) {
        log.info("enter {}", index);

        CloseableHttpResponse response;
        try {
            response = httpClient.execute(new HttpGet("http://www.baidu.com"));
            HttpEntity entity = response.getEntity();
            EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            log.error("catch connection timeout", e);
            return;
        }

        log.info("count: {} finished, status {}", index, response.getStatusLine());
    }
    
    public static void main(String[] args) {
            postDemo(1);
            postDemo(2);
    }

}
