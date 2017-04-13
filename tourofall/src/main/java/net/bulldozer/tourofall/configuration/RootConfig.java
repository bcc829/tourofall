package net.bulldozer.tourofall.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"net.bulldozer.tourofall.dao","net.bulldozer.tourofall.service"})
public class RootConfig {

}
