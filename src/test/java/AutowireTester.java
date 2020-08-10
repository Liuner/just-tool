import com.liugs.tool.constants.Console;
import com.liugs.tool.spring.autowire.CarBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName AutowireTester
 * @Description 自动注入测试类
 * @Author liugs
 * @Date 2020/8/7 17:10:56
 */
public class AutowireTester {

    @Test
    public void testAutowire() {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring-autowire-config.xml");
        CarBean car = (CarBean) beanFactory.getBean("carBean");
        Console.show("Brand：" + car.getBrand());
        car.run();
    }
}
