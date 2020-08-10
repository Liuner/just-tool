import com.liugs.tool.constants.Console;
import com.liugs.tool.spring.bean.BeanAble;
import com.liugs.tool.spring.bean.BeanManager;
import com.liugs.tool.spring.bean.TestBean;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName BeanAbleTester
 * @Description
 * @Author liugs
 * @Date 2020/8/7 14:34:43

 */
public class BeanAbleTester {

    @Test
    public void beanAbleTest() {

        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean-process-config.xml");

        TestBean bean = (TestBean) applicationContext.getBean("testBean");
        Console.show(bean.getBeanName());

        //获取管理类
        BeanManager manager = (BeanManager) applicationContext.getBean("beanManager");

        //从管理类中获取Bean
        BeanAble beanAble = manager.getBean(2);
        Console.show(beanAble.getBeanName());

        applicationContext.registerShutdownHook();
    }
}
