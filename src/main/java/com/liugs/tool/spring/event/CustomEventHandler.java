package com.liugs.tool.spring.event;


import com.liugs.tool.constants.Console;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName CustomEventHandler
 * @Description
 * @Author liugs
 * @Date 2020/8/10 15:53:21
 */
public class CustomEventHandler implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        Console.show(customEvent.toString());
    }
}
