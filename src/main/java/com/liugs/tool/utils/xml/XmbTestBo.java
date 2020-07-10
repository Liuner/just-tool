package com.liugs.tool.utils.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName XmbTestBo
 * @Description
 * @Author liugs
 * @Date 2020/7/10 16:20:13
 */
@XmlRootElement
public class XmbTestBo implements Serializable {

    private static final long serialVersionUID = 5282850015542928318L;

    private String lang;

    private String head;

    private XmbTestBodyBo body;

    @XmlElement(name = "Lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @XmlElement(name = "Head")
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @XmlElement(name = "Body")
    public XmbTestBodyBo getBody() {
        return body;
    }

    public void setBody(XmbTestBodyBo body) {
        this.body = body;
    }
}
