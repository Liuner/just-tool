import com.liugs.tool.spring.factory.AccountService;
import com.liugs.tool.spring.factory.ClientService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName FoctoryTester
 * @Description
 * @Author liugs
 * @Date 2020/8/12 14:29:04
 */
public class FactoryTester {

    @Test
    public void execute() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-factory-config.xml");

        ClientService clientService = context.getBean("clientService", ClientService.class);

        AccountService accountService = context.getBean("accountService", AccountService.class);

        clientService.print();
        accountService.print();
    }
}
