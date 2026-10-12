package com.oraclejava.domain.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.oraclejava.mailing_server.MailMail;

@Configuration
@MapperScan("com.oraclejava.domain.repository")
@ComponentScan(basePackages = {"com.oraclejava.domain"} )
@ImportResource("classpath:scheduler.xml")
@PropertySource("classpath:app.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	  public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
	    factoryBean.setDataSource(dataSource());
	    return factoryBean.getObject();
	  }
	
	@Bean
	public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	@Bean
	  public DataSourceTransactionManager transactionManager() {
	    return new DataSourceTransactionManager(dataSource());
	  }
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		 // 핵심: 타임아웃
		 prop.put("mail.smtp.connectiontimeout", "3000"); // 연결 대기 3초
	    prop.put("mail.smtp.timeout", "5000");           // 응답 대기 5초
	    prop.put("mail.smtp.writetimeout", "5000");      // 쓰기 대기 5초
	    
		mailSender.setJavaMailProperties(prop);
		return mailSender;
	}
	
	@Bean
	public MailMail mailMail() {
		MailMail mailMail = new MailMail();
		mailMail.setMailSender(mailSender());
		return mailMail;
	}
	
	

}
