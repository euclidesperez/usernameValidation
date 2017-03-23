package ve.com.eptest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages={"ve.com.eptest.usernameValidation"})
@PropertySources({
	@PropertySource("classpath:username.properties"),
	@PropertySource("classpath:reserveWords.properties"),
	@PropertySource("classpath:altern.properties")
})
public class AppConfig {
	@Autowired
	Environment env;

}
