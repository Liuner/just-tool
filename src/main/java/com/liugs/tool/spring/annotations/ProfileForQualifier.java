package com.liugs.tool.spring.annotations;

import com.liugs.tool.constants.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @ClassName ProfileForQualifier
 * @Description
 * @Author liugs
 * @Date 2020/8/10 10:24:01
 */
public class ProfileForQualifier {

    /**
     * Qualifier : 在有多个同类型的bean值时，可以指定需要的bean
     */

    @Autowired
    @Qualifier("demoForQualifier")
    private DemoForQualifier demoForQualifier;

    public ProfileForQualifier() {
        Console.show("Inside ProfileForQualifier constructor.");
    }

    public void printName() {
        Console.show("NAME：" + demoForQualifier.getName());
    }

    public void printAge() {
        Console.show("AGE：" + demoForQualifier.getAge());
    }

    public void printSex() {
        Console.show("SEX：" + demoForQualifier.getSex());
    }
}
