import com.liugs.tool.spring.annotations.ProfileForQualifier;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName QualifierTest
 * @Description
 * @Author liugs
 * @Date 2020/8/10 10:27:18
 */
public class QualifierTest {

    @Test
    public void execute() {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-qualifier-config.xml");

        ProfileForQualifier profileForQualifier = (ProfileForQualifier) applicationContext.getBean("profileForQualifier");
        profileForQualifier.printName();
        profileForQualifier.printAge();
        profileForQualifier.printSex();

        applicationContext.registerShutdownHook();
    }
}
