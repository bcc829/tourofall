package net.bulldozer.tourofall.config;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import net.bulldozer.tourofall.security.service.SpringSecuritySignInAdapter;
import net.bulldozer.tourofall.user.service.ConnectionManager;


@Configuration
public class SocialContext {
	@Resource
	private Environment env;
	
	@Inject
    private DataSource dataSource;
	
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository() {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), 
            Encryptors.noOpText());
    }
	
	@Bean
	public FacebookConnectionFactory facebookConnectionFactory(){
		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(env.getProperty("facebook.app.id"),env.getProperty("facebook.app.secret"));
		facebookConnectionFactory.setScope("public_profile,email");
		return facebookConnectionFactory;
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry connectionFactoryRegistry = new ConnectionFactoryRegistry();
		connectionFactoryRegistry.addConnectionFactory(facebookConnectionFactory());
		
		return connectionFactoryRegistry;
	}
	
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	 
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}
	
	@Bean
	public ConnectController connectController(){
		return new ConnectController(connectionFactoryLocator(),connectionRepository());
	}
	
	@Bean
	public SignInAdapter signInAdapter(){
		return new SpringSecuritySignInAdapter();
	}
	
	@Bean
	public ProviderSignInController providerSignInController(){ 
		return new ProviderSignInController(connectionFactoryLocator(),usersConnectionRepository(), signInAdapter());
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(){
		return new ProviderSignInUtils(connectionFactoryLocator(),usersConnectionRepository());
	}
	
	@Bean
	public ConnectionManager connectionManager(){
		return new ConnectionManager(facebookConnectionFactory() ,providerSignInUtils());
	}
}
