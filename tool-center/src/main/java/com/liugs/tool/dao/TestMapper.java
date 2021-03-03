package com.liugs.tool.dao;


import com.liugs.tool.dao.po.TestPo;

/**
 * TestMapper继承基类
 * @author Liuner
 */
public interface TestMapper {

    TestPo selectByName(String name);
}