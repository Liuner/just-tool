package com.liugs.tool.base;

import java.io.Serializable;

/**
 * @ClassName ToolBaseRspBo
 * @Description 基本出参
 * @Author liugs
 * @Date 2021/2/28 11:30:22
 */
public class ToolBaseRspBo implements Serializable {

   private static final long serialVersionUID = -9041664367104421437L;

    private String respCode;

    private String respDesc;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }
}