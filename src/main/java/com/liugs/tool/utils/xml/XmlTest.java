package com.liugs.tool.utils.xml;

import com.alibaba.fastjson.JSON;
import com.liugs.tool.constants.Console;

import java.util.Arrays;

/**
 * @ClassName XmlTest
 * @Description
 * @Author liugs
 * @Date 2020/7/10 16:24:16
 */
public class XmlTest {

    public static void main(String[] args) throws Exception {
        XmbTestBo testBo = new XmbTestBo();
        testBo.setHead("head");
        testBo.setLang("land");
        XmbTestBodyBo bodyBo = new XmbTestBodyBo();
        testBo.setBody(bodyBo);
        bodyBo.setOrderId("3232323");
        bodyBo.setjCompany("yellow");
        bodyBo.setMailNo("423423423423");
        String[] cargo = {"computer, mouse"};
        bodyBo.setCargo(Arrays.asList(cargo));
        String xml = XmlUtils.objToXml(testBo);


        String xmlStr = "<XmbTestBo>\n" +
                "    <Body orderid=\"3232323\">\n" +
                "        <cargo>computer, mouse</cargo>\n" +
                "        <mailNo>423423423423</mailNo>\n" +
                "        <company>汉字</company>\n" +
                "    </Body>\n" +
                "    <Head>head</Head>\n" +
                "    <Lang>land</Lang>\n" +
                "</XmbTestBo>";

        XmbTestBo newB = new XmbTestBo();
        XmbTestBodyBo xmbTestBodyBo = new XmbTestBodyBo();
        newB.setBody(xmbTestBodyBo);

        XmbTestBo bn = (XmbTestBo) XmlUtils.xmlToObj(xmlStr, XmbTestBo.class);
        Console.show(JSON.toJSONString(bn));
    }
}