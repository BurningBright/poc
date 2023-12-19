package cn.burningbright.value;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = {"classpath:a.yml"}, ignoreResourceNotFound = true, encoding = "utf-8")
public class BeanA {

    @Value("${test}")
    private String[] test;
    @Value("${car}")
    private Car car;

    @Value("#{${testMap:null}}")
    private Map<String, String> testMap;

    @Value("#{T(cn.burningbright.value.CarMappingDecoder).decodeMap('${carMapping:{}}')}")
    private Map<String, List<Car>> carDecodeMapping;

    @Value("classpath:a.yml")
    private Resource resourceFile;

    @Value("#{ferrari.name}")
    private String ferrariName;

    @Bean
    public Car ferrari(){
        return new Car("red", "f430");
    }

    public void test(){
        System.out.println("test:"+ Arrays.toString(test));
        System.out.println("长度："+test.length);

        System.out.println("自定的Car 居然通过@Value注册成功了");
        System.out.println(car.toString());

        System.out.println("testMap:");
        System.out.println(JSON.toJSONString(testMap));

        System.out.println("carDecodeMap:");
        System.out.println(JSON.toJSONString(carDecodeMapping));


        try {
            InputStreamReader iReader = new InputStreamReader(resourceFile.getInputStream(), "UTF-8");
            BufferedReader bReader = new BufferedReader(iReader);
            System.out.println("file:");
            System.out.println(bReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("beanInject:");
        System.out.println(ferrariName);

    }

}