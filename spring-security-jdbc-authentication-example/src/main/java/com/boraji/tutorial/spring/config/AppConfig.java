package com.boraji.tutorial.spring.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.boraji.tutorial.spring.model.Authorities;
import com.boraji.tutorial.spring.model.User;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.boraji.tutorial.spring.dao"),
		@ComponentScan("com.boraji.tutorial.spring.service") })
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		Properties props = new Properties();

		// Setting JDBC properties
		props.put(DRIVER, "com.mysql.jdbc.Driver");
		props.put(URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
		props.put(USER, "root");
		props.put(PASS, "");

		// Setting Hibernate properties
		props.put(SHOW_SQL, true);
		props.put(HBM2DDL_AUTO, "update");

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, 2);
		props.put(C3P0_MAX_SIZE, 20);
		props.put(C3P0_ACQUIRE_INCREMENT, 1);
		props.put(C3P0_TIMEOUT, 1800);
		props.put(C3P0_MAX_STATEMENTS, 150);
		
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(User.class, Authorities.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
