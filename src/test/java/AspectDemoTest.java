import com.liugs.tool.constants.Console;
import com.liugs.tool.spring.aop.ConfigAopDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName AspectDemoTest
 * @Description
 * @Author liugs
 * @Date 2020/8/10 17:01:34
 */
public class AspectDemoTest {

    @Test
    public void execute() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-config.xml");

        ConfigAopDemo demo = (ConfigAopDemo)context.getBean("configAopDemo");
        Console.show("=====================NAME：" + demo.getName());
        Console.show("=====================AGE：" + demo.getAge());
        demo.printThrowException();
    }
}
