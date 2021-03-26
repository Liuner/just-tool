package com.liugs.tool.test;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.liugs.tool.base.Console;

/**
 * @ClassName ASearchTest
 * @Description 搜索测试
 * @Author liugs
 * @Date 2021/3/25 9:29:18
 */
public class ASearchTest {

    private static int OPERATE_TYPE;
    private static String URL = "http://localhost:8081/search/elastic/";

    public static void main(String[] args) {
        OPERATE_TYPE = 3;
        switch (OPERATE_TYPE) {
            case 1:
                addDocument();
                break;
            case 2:
                getDocById();
                break;
            case 3:
                syncDataToEs();
                break;
            default:
                break;
        }
    }

    /**
     * 描述 同步数据到ES
     * @param
     * @return void
     * @author liugs
     * @date 2021/3/25 14:49:17
     */
    private static void syncDataToEs() {
        URL = URL + "syncAllCommodityToEs";

        JSONObject reqData = new JSONObject();

        Console.show(HttpUtil.post(URL, reqData.toJSONString()));
    }

    /**
     * 描述 根据文章ID查询文章
     * @param
     * @return void
     * @author liugs
     * @date 2021/3/25 14:15:30
     */
    private static void getDocById() {
        URL = URL + "utilTest";
        JSONObject reqData = new JSONObject();
        reqData.put("operateType", "2");
        reqData.put("indexName", "index_test");
        reqData.put("docId", "2021032501");


        Console.show(reqData.toJSONString());

        String result = HttpUtil.post(URL, reqData.toJSONString());
        Console.show(result);
    }

    /**
     * 描述 新增文章
     * @param
     * @return void
     * @author liugs
     * @date 2021/3/25 9:32:17
     */
    private static void addDocument() {
        URL = URL + "utilTest";

        JSONObject reqData = new JSONObject();
        reqData.put("operateType", "1");
        reqData.put("indexName", "index_test");
        reqData.put("docId", "2021032501");

        JSONObject source = new JSONObject();
        source.put("age", "25");
        source.put("description", "RestClient Api Test");
        source.put("name", "liugs_test1");
        source.put("update_test", "12121");
        source.put("weight", "1111");

        JSONObject nameExtData = new JSONObject();
        nameExtData.put("first_name", "liu");
        nameExtData.put("last_name", "gs");

//        source.put("name_ext", nameExtData.toJSONString());
        reqData.put("source", source.toJSONString());

        Console.show(reqData.toJSONString());

        String result = HttpUtil.post(URL, reqData.toJSONString());
        Console.show(result);
    }
}
