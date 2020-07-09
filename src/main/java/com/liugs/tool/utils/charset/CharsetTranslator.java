package com.liugs.tool.utils;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CharsetTranslator
 * @Description 编码转换
 * @Author liugs
 * @Date 2020/5/29 11:22:40
 */
public class CharsetTranslator {

    /**
     * 原编码
     */
    private static String resCharset;
    /**
     * 目标编码
     */
    private static String tarCharset;

    public static void main(String[] args) {
        resCharset = "GBK";
        tarCharset = "UTF-8";

        //以下文件格式的，不转
        String suffix = "png,json,docx,mapper,dao,xml,gitignore,txt,md,iml,lock,vue";
        String[] strings = suffix.split(",");
        List<String> noTranceFileSuffix = Arrays.asList(strings);

        //以下文件夹，不转
        String directories = "images,node_modules,resources,target,.git,.idea,apoms,init,logs,ulc-web-jar,bo,ulc-web-vue";
        String[] directoriesString = directories.split(",");
        List<String> noTranceDir = Arrays.asList(directoriesString);

        //文件路径
        String path = "E:\\devFile\\documentCenter\\新建文件夹";
        //转码
//        translate(path, noTranceFileSuffix, noTranceDir);

        //目标文件绝对路径
        String targetAbsolutePath = "E:\\Users\\Liuner\\Desktop\\allCode.txt";
        resCharset = "GBK";
        tarCharset = "UTF-8";
        path = "E:\\all_Codes\\boot-ulc";

        //将所有文件读取到一个文件
        getAllFile(path, targetAbsolutePath, noTranceFileSuffix, noTranceDir);
    }


    public static void translate(String path, List<String> noTranceFileSuffix, List<String> noTranceDir) {
        List<String> fileAbsolutePaths = new ArrayList<>();
       getFilePath(path, fileAbsolutePaths,noTranceFileSuffix, noTranceDir);
        for (String fileAbsolutePath : fileAbsolutePaths) {
            String curCodeContent = FileUtil.readString(fileAbsolutePath, resCharset);
            FileUtil.writeString(curCodeContent, fileAbsolutePath, tarCharset);
            System.out.println("TRANSLATED：" + fileAbsolutePath);
        }
    }

    public static void getAllFile(String path, String targetAbsolutePath, List<String> noTranceFileSuffix, List<String> noTranceDir) {
        List<String> fileAbsolutePaths = new ArrayList<>();
        getFilePath(path, fileAbsolutePaths, noTranceFileSuffix, noTranceDir);
        for (String fileAbsolutePath : fileAbsolutePaths) {
            String curCodeContent = FileUtil.readString(fileAbsolutePath, tarCharset);
            FileUtil.appendString(curCodeContent, targetAbsolutePath, tarCharset);
            System.out.println("APPENDED FILE：" + fileAbsolutePath);
        }
    }


    private static void getFilePath(String path, List<String> fileAbsolutePaths, List<String> noTranceFileSuffix, List<String> noTranceDir) {
        //获取路径下的所有文件或目录
        File[] files = new File(path).listFiles();
        if (null == files) {
            System.out.println("ERROR ：" + path + ", there is no file");
            return;
        }
        for (File file : files) {
            String fileAbsolutePath = file.getAbsolutePath();
            if (file.isDirectory()) {
                if (noTranceDir.contains(file.getName())) {
                    System.out.println("This folder: " + file.getName() + " does not need to be converted");
                    continue;
                }
                getFilePath(fileAbsolutePath, fileAbsolutePaths, noTranceFileSuffix, noTranceDir);
            } else {
                String fileName = file.getName();
                try {
                    String charset = getCharset(file.getAbsoluteFile());
//                    if (!resCharset.equals(charset)) {
//                        System.out.println("File " + fileName + "`s charset is not consistent with what you typed." + charset);
//                        continue;
//                    }
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if (noTranceFileSuffix.contains(suffix)) {
                        System.out.println("This file: " + fileName + " does not need to be converted");
                        continue;
                    }
                    fileAbsolutePaths.add(fileAbsolutePath);
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    private static String getCharset(File file) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
        int p = (bin.read() << 8) + bin.read();
        String code;
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                code = "GBK";
        }
        return code;
    }
}
