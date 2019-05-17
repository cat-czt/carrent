package com.qc.information.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: czt
 * @Date: 18-11-2 下午1:35
 */
public class MapUtil {


    private static final Logger logger = LoggerFactory.getLogger(MapUtil.class);

    /**
     * 将对应实体类参数转为map类型同时去除至为null的字段
     * @param o
     * @return
     */
    public static Map<String, String> objectToMap(Object o) {
        Map<String, String> map = new HashMap<>();
        if (!ObjectUtils.isEmpty(o)) {
            Class c = o.getClass();
            Field[] fields = c.getDeclaredFields();
            if (fields != null) {
                for (Field f : fields) {
                    f.setAccessible(true);//设置可访问权限 可获取到私有属性的value
                    String name = f.getName();
                    //获取属性的值
                    Object value = null;
                    try {
                        value = f.get(o);
                    } catch (IllegalAccessException e) {
                        logger.error("----> IllegalAccessException_" + e.getMessage());
                        e.printStackTrace();
                    }
                    if (!ObjectUtils.isEmpty(value)) {
                        map.put(name, value.toString());
                    }
                }
            }

        }
        return map;
    }

}
