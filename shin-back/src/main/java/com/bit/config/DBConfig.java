package com.bit.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan()
public class DBConfig {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		
		ds.setDriverClass(env.getProperty("db.driver"));
		ds.setJdbcUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.user"));
		ds.setPassword(env.getProperty("db.keyword"));
		ds.setIdleConnectionTestPeriodInMinutes(60l);
		ds.setIdleMaxAgeInMinutes(60);
		ds.setMaxConnectionsPerPartition(45);
		ds.setMinConnectionsPerPartition(10);
		ds.setPartitionCount(4);
		ds.setAcquireIncrement(5);
		ds.setStatementsCacheSize(10);
		
		return ds;
	}
	
	@Bean
	public BeanPostProcessor persistentTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		try {
			System.out.println( "Creando sessionfactory" );
			LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
			lsfb.setDataSource(dataSource());
			lsfb.setPackagesToScan("com.bit.model");
			Properties props = new Properties();
			props.setProperty("hibernate.dialect",env.getProperty("db.dialect"));
			props.setProperty("show_sql","false");
			lsfb.setHibernateProperties(props);
			lsfb.afterPropertiesSet();
			return lsfb;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		
		return tx;
	}
}
