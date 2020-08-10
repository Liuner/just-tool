package com.liugs.tool.utils.charset;

import com.liugs.tool.constants.Console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileOption
 * @Description
 * @Author liugs
 * @Date 2020/8/6 15:33:47
 */
public class FileOption {

    public static void main(String[] args) throws IOException {
       /* String path = "E:\\Liunuer\\Documents\\code\\just-tool\\src\\main\\java\\com\\liugs\\tool\\utils\\multithread\\demo\\ReadTest.txt";
        //读取文件
        List<String> content = read(path);
        for (String str : content) {
            Console.show(str);
        }

        String newFilePath = "E:\\Liunuer\\Documents\\code\\just-tool\\src\\main\\java\\com\\liugs\\tool\\utils\\multithread\\demo\\WriteTest.txt";
        //写入文件
        writeFile(content, newFilePath);*/

        consoleInput();
    }


    /**
     * 描述 读取文件
     * @param filePath
     * @return java.util.List<java.lang.String>
     * @author liugs
     * @date 2020/8/6 15:45:53
     */
    public static List<String> read(String filePath) throws IOException {
        List<String> content = new ArrayList<>();
        File file = new File(filePath);
        //文件不存在则新建
        if (!file.exists()) {
            file.createNewFile();
        }
        //可指定编码
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        /**==========================================================*/
        /*FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);*/
        /**==========================================================*/

        String line = bufferedReader.readLine();
        while (line != null) {
            content.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();

        return content;
    }

    /**
     * 描述 写文件
     * @param strs, filePath
     * @return void
     * @author liugs
     * @date 2020/8/6 15:47:17
     */
    public static void writeFile(List<String> strs, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        //可指定编码
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        /**========================================================*/
        /*FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);*/
        /**========================================================*/

        for (String content : strs) {
            bufferedWriter.write(content);
            bufferedWriter.write("\r\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
        Console.show("文件写入完成");
    }


    public static void deal8bit() throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream("input.txt");
            outputStream = new FileOutputStream("output.txt");

            int c;
            while (-1 != (c = inputStream.read())) {
                outputStream.write(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
        }
    }

    public static void deal16UniCode() throws IOException {
        FileReader in = null;
        FileWriter out = null;

        int c;
        try {
            in = new FileReader("input.txt");
            out = new FileWriter("output.txt");
            while (-1 != (c = in.read())) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != out) {
                out.close();
            }
        }
    }

    public static void consoleInput() throws IOException {
        File file = new File("E:\\Liunuer\\Documents\\code\\just-tool\\src\\main\\java\\com\\liugs\\tool\\utils\\multithread\\demo\\SystemInTest.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String content = "";
        System.out.print("输入'exit'退出");
        while (true) {
            System.out.print("请输入：");
            content = bufferedReader.readLine();
            if ("exit".equals(content)) {
                break;
            }
            bufferedWriter.write(content);
            bufferedWriter.write("\r\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        fileWriter.close();

        bufferedReader = new BufferedReader(new FileReader(file));
        inputStreamReader.close();

        content = bufferedReader.readLine();
        while (null != content) {
            Console.show(content);
            content = bufferedReader.readLine();
        }
        bufferedReader.close();
    }
}
