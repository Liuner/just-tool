package com.liugs.tool.utils.charset;

import com.liugs.tool.constants.Console;
import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FileCompare
 * @Description 文件比较
 * @Author liugs
 * @Date 2020/8/14 14:34:32
 */
public class FileCompare {

    public static void readFile(String source, String target) {
        File[] sourceFile = new File(source).listFiles();
        File[] targetFile = new File(target).listFiles();

        if (null == sourceFile || null == targetFile) {
            Console.show("source and target can not be null");
            return;
        }

        File[] max = sourceFile;
        File[] min = targetFile;
        if (max.length < min.length) {
            max = targetFile;
            min = sourceFile;
        }

        Map<String, Integer> tempMap = new HashMap<>(16);
        List<String> diff = new ArrayList<>();

        List<String> differences = new ArrayList<>();
        for (File sFile : max) {
            tempMap.put(sFile.getName(), 1);
        }
        for (File tFile : min) {
            String fileName = tFile.getName();
            if(tempMap.get(fileName) != null) {
                tempMap.put(fileName, 2);
                continue;
            }
            diff.add("目标文件夹独有的文件：" + tFile.getAbsolutePath());
        }



    }

    /**
     * 比较文件
     * @param source
     * @param target
     */
    public static void compareFile(File source, File target) {

    }

    /**
     * 描述 比较两个文件的md5
     * @param source, target
     * @return void
     * @author liugs
     * @date 2020/8/14 15:01:39
     */
    public static Boolean compareMD5(File source, File target) {
        if (!source.isFile() || !target.isFile()) {
            return null;
        }

        return getFileMD5(source).equals(getFileMD5(target));
    }

    /**
     * 获取文件的md5值
     * @return md5 value
     */
    public static String getFileMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}