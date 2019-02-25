package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import data.domain.FromClientMessage;
import data.domain.ToClientMessage;

import java.util.List;

/**
 * 将json解析为相应的业务对象
 */
public class JsonUtil {

    public static FromClientMessage analysis(String json) {
        FromClientMessage fromClientMessage;
        try {
            fromClientMessage = JSON.parseObject(json, FromClientMessage.class);
            return fromClientMessage;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object analysis(Object data, Class<?> clazz) {
        if (data != null) {
            return JSON.parseObject(String.valueOf(data), clazz);
        } else {
            return null;
        }
    }

    public static ToClientMessage pack(String json) {
        return JSON.parseObject(json, ToClientMessage.class);
    }

    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

}
