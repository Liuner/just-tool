package com.liugs.tool.utils.charset;

import com.liugs.tool.constants.Console;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @ClassName ChineseToPinyin
 * @Description 汉字转拼音
 * @Author liugs
 * @Date 2020/8/6 14:54:19
 */
public class ChineseToPinyin {

    public static void main(String[] args) {
        String str = "你好!我是测试汉字!";
//        str = conver(str);
        Console.show(str);

        str = getPinYinHeadChar(str);
        Console.show(str);
    }


    /**
     * 描述 汉字转拼音
     * @param resource
     * @return java.lang.String
     * @author liugs
     * @date 2020/8/6 15:20:11
     */
    public static String conver(String resource)  {
        char[] chars = null;
        chars = resource.toCharArray();

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String[] pinyinStrs;
        String targetStr = "";
        try {
            for (char c : chars) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    pinyinStrs = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    targetStr += pinyinStrs[0];
                } else {
                    targetStr += Character.toString(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return targetStr;
    }

    /**
     * 描述 获取拼音首字母
     * @param resourceStr
     * @return java.lang.String
     * @author liugs
     * @date 2020/8/6 15:14:25
     */
    public static String getPinYinHeadChar(String resourceStr) {
        String convert = "";

        for (int j = 0; j < resourceStr.length(); j++) {
            char word = resourceStr.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }

        return convert.toUpperCase();
    }
}
