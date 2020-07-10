package com.liugs.tool.utils.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName XmlUtils
 * @Description XML工具类
 * @Author liugs
 * @Date 2019/7/17 11:25:12
 */
public class XmlUtils {
    /**
     * 描述:
     * @param object
     * @return java.lang.String
     * @author liugs
     * @date 2019/7/17 11:27:06
     */
    public static String objToXml(Object object) throws Exception {
        //获取转换的上下文对象
        JAXBContext context = JAXBContext.newInstance(object.getClass());

        //获取Marshaller对象
        Marshaller marshaller = context.createMarshaller();

        //xml格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //去掉生成xml的默认报文头
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        //将对象转换为xml
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);
        //打印一下
        System.out.println("----------------result of objToXml----------------\n" + sw.toString());

        return sw.toString();
    }

    /**
     * 描述:
     * @param xmlStr, tClass
     * @return java.lang.Object
     * @author liugs
     * @date 2019/7/17 11:37:04
     */
    public static Object xmlToObj(String xmlStr, Class tClass) throws Exception{
        //获取JAXB的上下文对象
        JAXBContext context = JAXBContext.newInstance(tClass);

        //获取UnMarshaller对象实例
        Unmarshaller unmarshaller = context.createUnmarshaller();

        //转换需要转换的XML数据 （如果不指定编码格式，会按照环境默认的格式编码，解析时可能会报异常）
        InputStream stream = new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8));

        //将XML数据序转换为 Object
        return unmarshaller.unmarshal(stream);
    }
}
