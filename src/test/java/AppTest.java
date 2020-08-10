import com.liugs.tool.spring.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName AppTest
 * @Description
 * @Author liugs
 * @Date 2020/8/3 17:13:35
 */
public class AppTest {

	@Test
	public void MyTestBeanTest() {
//		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));

		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		MyTestBean bean = (MyTestBean) applicationContext.getBean("myTestBean");
		System.out.println(bean.getName());
		bean.setName("before_you_defined_me_i_was_defined");

		applicationContext.registerShutdownHook();


		//测试bean的属性
		/*MyTestBean bean1 = (MyTestBean) applicationContext.getBean("myTestBean");
		System.out.println(bean1.getName());*/

		/*ApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\Liunuer\\Documents\\code\\spring-study\\src\\main\\resources\\spring-config.xml");
		MyTestBean bean = (MyTestBean) applicationContext.getBean("myTestBean");
		System.out.println(bean.getName());*/
	}
}
