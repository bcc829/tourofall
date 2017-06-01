package net.bulldozer.tourofall.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages={
		"net.bulldozer.tourofall.answer.repository",
		"net.bulldozer.tourofall.question.repository",
		"net.bulldozer.tourofall.review.repository",
		"net.bulldozer.tourofall.user.repository",
		"net.bulldozer.tourofall.recommendation.repository",
		"net.bulldozer.tourofall.evaluation.repository",
		"net.bulldozer.tourofall.destination.repository"
})
@EnableTransactionManagement
public class PersistenceContext {
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:data-schema.sql")
				.addScript("classpath:data-insert.sql")
				.build();
		
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getRequiredProperty("mysql.driverClassName"));
//		dataSource.setUrl(env.getRequiredProperty("mysql.url"));
//		dataSource.setUsername(env.getRequiredProperty("mysql.username"));
//		dataSource.setPassword(env.getRequiredProperty("mysql.password"));
//		return dataSource;
	}
	
	@Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
 
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(new String[]{
                "net.bulldozer.tourofall.question.dto",
                "net.bulldozer.tourofall.answer.dto",
                "net.bulldozer.tourofall.review.dto",
                "net.bulldozer.tourofall.user.dto",
                "net.bulldozer.tourofall.evaluation.dto",
                "net.bulldozer.tourofall.destination.dto"
        });
 
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }
}
