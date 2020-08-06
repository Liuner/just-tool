package com.liugs.tool.utils.charset;

import com.liugs.tool.constants.Console;

/**
 * @ClassName StringToASCII
 * @Description 字符串转ASC码
 * @Author liugs
 * @Date 2020/8/6 15:22:23
 */
public class StringToASCII {

    public static void main(String[] args) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        str = convert(str);
        Console.show(str);
    }

    public static String convert(String resStr) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bGBK = resStr.getBytes();
        for (byte b : bGBK) {
            stringBuffer.append(Integer.toHexString(b & 0xff)).append(" ");
        }
        return stringBuffer.toString();
    }
}
