package cn.burningbright.value;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class CarMappingDecoder {

    public static Map<String, List<Car>> decodeMap(String value) {
        try {
            return JSONObject.parseObject(value, new TypeReference<Map<String, List<Car>>>(){});
        } catch (Exception e) {
            return null;
        }
    }

}