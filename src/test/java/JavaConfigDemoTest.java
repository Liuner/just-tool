import com.liugs.tool.constants.Console;
import com.liugs.tool.spring.annotations.Computer;
import com.liugs.tool.spring.annotations.JavaConfigImplDemo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName JavaConfigDemoTest
 * @Description
 * @Author liugs
 * @Date 2020/8/10 11:08:40
 */
public class JavaConfigDemoTest {

    @Test
    public void execute() {
//        AnnotationConfigApplicationContext  applicationContext = new AnnotationConfigApplicationContext(JavaConfigDemo.class);

        //import测试
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfigImplDemo.class);


        Computer computer = applicationContext.getBean(Computer.class);
        computer.star();

        applicationContext.registerShutdownHook();

        Console.show("======================================");
       /*
        //注册bean
        applicationContext.register(DemoForQualifier.class, ProfileForQualifier.class);

        DemoForQualifier demoForQualifier = applicationContext.getBean(DemoForQualifier.class);
        demoForQualifier.setName("PORSCHE");
        demoForQualifier.setAge(99);
        demoForQualifier.setSex("CAR");

        ProfileForQualifier qualifier = applicationContext.getBean(ProfileForQualifier.class);
        qualifier.printName();
        qualifier.printAge();
        qualifier.printSex();*/
    }
}
