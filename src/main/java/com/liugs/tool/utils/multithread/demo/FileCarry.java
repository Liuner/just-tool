package com.liugs.tool.utils.multithread.demo;

/**
 * @ClassName FileCarry
 * @Description 文件承载对象
 * @Author liugs
 * @Date 2020/7/22 16:45:24
 */
public class FileCarry implements Comparable<FileCarry> {

    private Integer index;
    private String content;

    FileCarry(Integer index, String content) {
        this.content = content;
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(FileCarry o) {
        return o.getIndex() - this.index;
    }
}
