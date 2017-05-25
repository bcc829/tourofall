package net.bulldozer.tourofall.config;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages={
		"net.bulldozer.tourofall.destination.service",
		"net.bulldozer.tourofall.question.service",
		"net.bulldozer.tourofall.answer.service",
		"net.bulldozer.tourofall.review.service",
		"net.bulldozer.tourofall.user.service",
		"net.bulldozer.tourofall.recommendation.service",
		"net.bulldozer.tourofall.evaluation.service"
})
@Import({PersistenceContext.class,SecurityContext.class})
@PropertySource("classpath:application.properties")
public class RootConfig {
	@Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.getMessageConverters().add(1,new MappingJackson2HttpMessageConverter());
		
		return restTemplate;
	}
	
}
 