package hub;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pojo.Product;

/**
 * hibernate的基本步骤是：
1. 获取SessionFactory
2. 通过SessionFactory 获取一个Session
3. 在Session基础上开启一个事务
4. 通过调用Session的save方法把对象保存到数据库
5. 提交事务
6. 关闭Session
7. 关闭SessionFactory
 */
public class TestHibernate {
	public static void main(String[] args) {
		//1、加载Hibernate的核心配置文件：hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();
		//2、创建一个SessionFactory对象：类似于JDBC中的连接池
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3、通过SessionFactory获取Session对象：类似JDBC中的Connection
		Session session = sessionFactory.openSession();
		//4、手动开启事务
		Transaction transaction = session.beginTransaction();
		//5、编写代码
		
		for (int i = 1; i <=10; i++) {
			Product p = new Product();
			p.setName("iphone"+i);
			p.setPrice(1000*i);
			session.save(p);
		}
		//6、事务提交
		transaction.commit();
		//7、资源释放
		session.close();
		sessionFactory.close();

	
	
	}	
}
