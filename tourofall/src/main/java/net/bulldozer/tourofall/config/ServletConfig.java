package net.bulldozer.tourofall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@Import({SocialContext.class})
@EnableWebMvc
@ComponentScan(basePackages={
		"net.bulldozer.tourofall.common.controller",
		"net.bulldozer.tourofall.destination.controller",
		"net.bulldozer.tourofall.question.controller",
		"net.bulldozer.tourofall.review.controller",
		"net.bulldozer.tourofall.user.controller",
		"net.bulldozer.tourofall.recommendation.controller",
		"net.bulldozer.tourofall.evaluation.controller"
})
public class ServletConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[]{
				"/WEB-INF/tiles-definitions/tiles-def.xml",
				"/WEB-INF/tiles-definitions/base-common-tiles.xml",
				"/WEB-INF/tiles-definitions/base-destinfo-tiles.xml",
				"/WEB-INF/tiles-definitions/base-detailinfo-tiles.xml",
				"/WEB-INF/tiles-definitions/base-introinfo-tiles.xml",
				"/WEB-INF/tiles-definitions/base-users-tiles.xml",
				"/WEB-INF/tiles-definitions/destinfo-tiles.xml",
				"/WEB-INF/tiles-definitions/users-tiles.xml"
				});
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver(){
		return new TilesViewResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
