package com.liugs.tool.constants;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ToolPropertiesKey
 * @Description 配置文件key
 * @Author liugs
 * @Date 2020/9/8 14:08:41
 * @Copyright 2020 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class ToolPropertiesKey {

    /***/
    public static String TEST_KEY = "testKey";

    public static  Set<String> getKeySet() {
        Set<String> keySet = new HashSet<>(16);
        Map<String, Field> map = ReflectUtil.getFieldMap(ToolPropertiesKey.class);
        Iterator<Map.Entry<String, Field>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Field> entry = iterator.next();
            Field field = entry.getValue();
            try {
                keySet.add((String) field.get(entry.getKey()));
            } catch (Exception e) {
                System.out.println("从ToolPropertiesKey中获取配置文件key异常：" + e);
            }
        }
        return keySet;
    }
}
