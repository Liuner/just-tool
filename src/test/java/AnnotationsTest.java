import com.liugs.tool.spring.annotations.Computer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName AnnotationsTest
 * @Description 注解测试
 * @Author liugs
 * @Date 2020/8/7 17:52:49

 */
public class AnnotationsTest {

    @Test
    public void star() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-annotations-config.xml");
        Computer computer = (Computer) applicationContext.getBean("computer");
        computer.star();
    }
}
