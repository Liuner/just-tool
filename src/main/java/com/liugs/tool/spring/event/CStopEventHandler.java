package com.liugs.tool.spring.event;

import com.liugs.tool.constants.Console;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @ClassName CStopEventHandler
 * @Description
 * @Author liugs
 * @Date 2020/8/10 15:13:26
 */
public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        Console.show("ContextStoppedEvent Received");
    }
}
