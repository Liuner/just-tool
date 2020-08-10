package com.liugs.tool.spring.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName JavaConfigImplDemo
 * @Description
 * @Author liugs
 * @Date 2020/8/10 11:31:10
 */
@Configuration
@Import(JavaConfigDemo.class)
public class JavaConfigImplDemo {

    public Computer computer() {
        return computer();
    }
}
