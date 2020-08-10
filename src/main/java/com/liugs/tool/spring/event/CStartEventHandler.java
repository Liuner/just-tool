package com.liugs.tool.spring.event;


import com.liugs.tool.constants.Console;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;


/**
 * @ClassName CStartEventHandler
 * @Description
 * @Author liugs
 * @Date 2020/8/10 15:04:17
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        Console.show("ContextStartedEvent Received");
    }
}
