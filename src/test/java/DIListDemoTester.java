import com.liugs.tool.constants.Console;
import com.liugs.tool.spring.DIListDemo;
import com.liugs.tool.spring.bean.DITest;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName DIListDemoTester
 * @Description
 * @Author liugs
 * @Date 2020/8/7 16:44:44
 */
public class DIListDemoTester {

    @Test
    public void DIList() {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-list-config.xml");

        DIListDemo demo = (DIListDemo) applicationContext.getBean("dIListDemo");

        Console.show("list：" + demo.getList());
        Console.show("set：" + demo.getSet());
        Console.show("map：" + demo.getMap());
        Console.show("properties：" + demo.getProperties());
        Console.show("DIList：" + demo.getDIList());

        DITest diTest = (DITest) demo.getDIList().get(0);
        diTest.print();

        Console.show("NUllValue：" + demo.getNullValue());
    }
}
