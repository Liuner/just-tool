package com.liugs.tool.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName CustomEvent
 * @Description 自定义事件
 * @Author liugs
 * @Date 2020/8/10 15:32:51
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    @Override
    public String toString(){
        return "My Custom Event";
    }
}