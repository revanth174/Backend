package com.KRJS.Back.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.KRJS.Back.dao.MemberDao;
import com.KRJS.Back.daoimpl.MemberDaoImpl;
import com.KRJS.Back.model.Address;
import com.KRJS.Back.model.Details;
import com.KRJS.Back.model.Member;
import com.KRJS.Back.model.Payment;

@Configuration

@EnableTransactionManagement
public class ApplicationContextConfig {

	// data base properties

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mmm?autoReconnect=true&useSSL=false";
	private static final String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "7396";

	// datasource is another means of connection database.
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getMysqlDataSource() {

		System.out.println("datasource");
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(DATABASE_DRIVER);
		datasource.setUrl(DATABASE_URL);
		datasource.setUsername(DATABASE_USERNAME);
		datasource.setPassword(DATABASE_PASSWORD);
		System.out.println("data source created");
		return datasource;
	}
	
	
	
	/*@Autowired
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		System.out.println("Data Source Method");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/sss");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
	
		System.out.println("Data Source Created");
		return dataSource;
	}*/
	
	

	// setting hibernate properties
	public Properties getHibernateProperties() {
		System.out.println("Hibernate properties");
		Properties prop = new Properties();
		prop.put("hibernate.dialect", DATABASE_DIALECT);
		//properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		System.out.println("hibernate properties created");
		return prop;
	}
	
	@Autowired
	@Bean(name="dao")
	public MemberDao getMem(SessionFactory sessionFactory) {
		return new MemberDaoImpl(sessionFactory);
	}

	// session factory
	// @Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		System.out.println("session factory");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Member.class);
		sessionBuilder.scanPackages("com.KRJS.Back.model");
		sessionBuilder.addAnnotatedClass(Details.class);
		sessionBuilder.addAnnotatedClass(Payment.class);
		sessionBuilder.addAnnotatedClass(Address.class);
		//sessionBuilder.addAnnotatedClass(Student.class);
		System.out.println("session factory object creates");
		return sessionBuilder.buildSessionFactory();
	}

	
	@Autowired
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		System.out.println("in transaction manager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("returns trasaction manager");
		return transactionManager;
		
	}

}
