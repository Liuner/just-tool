package com.liugs.tool.utils.xml;

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

    }
}