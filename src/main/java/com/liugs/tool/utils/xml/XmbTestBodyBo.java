package com.liugs.tool.utils.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName XmbTestBodyBo
 * @Description
 * @Author liugs
 * @Date 2020/7/10 16:28:01
 */

public class XmbTestBodyBo implements Serializable {

    private static final long serialVersionUID = 8627849469395943108L;

    private String orderId;

    private String mailNo;

    private String jCompany;

    private List<String> cargo;

    @XmlAttribute(name = "orderid")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    @XmlElement(name = "mailNo")
    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    @XmlElement(name = "company")
    public String getjCompany() {
        return jCompany;
    }

    public void setjCompany(String jCompany) {
        this.jCompany = jCompany;
    }

    @XmlElement(name = "cargo")
    public List<String> getCargo() {
        return cargo;
    }

    public void setCargo(List<String> cargo) {
        this.cargo = cargo;
    }
}
