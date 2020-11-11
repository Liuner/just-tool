package com.liugs.tool.utils;

import com.liugs.tool.constants.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GetSubmitAutoForm
 * @Description
 * @Author liugs
 * @Date 2020/11/11 14:01:47
 * @Copyright 2020 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class GetSubmitAutoForm {

    private static String formHead = "<form name=\"message_form\" method=\"post\" action=\"URL\">";
    private static String inputItem = "<input type=\"hidden\" name=\"CODE\" value=\"VALUE\">";
    private static String submit = "<input type=\"submit\" value=\"立即支付\" style=\"display:none\">";
    private static String script = "<script>document.forms[0].submit();</script>";

    public static String getForm(String url, Map<String, Object> paraMap) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append(formHead.replace("URL", url));
        for (String key : paraMap.keySet()) {
            String input = inputItem.replace("CODE", key).replace("VALUE", (String)paraMap.get(key));
            htmlBuilder.append(input);
        }
        htmlBuilder.append(submit);
        htmlBuilder.append("</form>");
        htmlBuilder.append(script);
        Console.show("组装好的HTML为：" + htmlBuilder.toString());
        return htmlBuilder.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> paraMap = new HashMap<>(16);
        paraMap.put("test", "test");
        getForm("http://liugs.utools.club/pay/rest/acceptPayCenterCallback", paraMap);
    }
}
