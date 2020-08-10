package com.liugs.tool.spring;


import com.liugs.tool.spring.bean.DITest;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName DIListDemo
 * @Description 注入集合demo
 * @Author liugs
 * @Date 2020/8/7 16:42:29
 */
public class DIListDemo {
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties properties;

    private List<DITest> DIList;

    private String nullValue;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<DITest> getDIList() {
        return DIList;
    }

    public void setDIList(List<DITest> DIList) {
        this.DIList = DIList;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }
}
