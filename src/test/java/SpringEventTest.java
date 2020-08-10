import com.liugs.tool.spring.event.CustomEventPublisher;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

;

/**
 * @ClassName SpringEventTest
 * @Description
 * @Author liugs
 * @Date 2020/8/10 15:15:24
 */
public class SpringEventTest {

    @Test
    public void execute() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-event-config.xml");
/*
        //启动事件
        context.start();

        Born born = (Born) context.getBean("born");

        Console.show(born.getMessage());

        //停止事件
        context.stop();*/

        CustomEventPublisher publisher = (CustomEventPublisher) context.getBean("customEventPublisher");
        publisher.publish();

        context.registerShutdownHook();
    }
}
