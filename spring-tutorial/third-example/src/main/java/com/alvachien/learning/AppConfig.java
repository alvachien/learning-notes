package com.alvachien.learning;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class AppConfig {
	@Bean
	@Primary
	@Qualifier("z")
	ZoneId createZoneOfZ() {
		return ZoneId.of("Z");
	}

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.register("sam@example.com", "password", "Sam");
		User user = userService.login("sam@example.com", "password");
		System.out.println(user.getName());
		context.getBean(MailSession.class);
		context.getBean(MailSession.class);
		context.getBean(MailSession.class);
		((ConfigurableApplicationContext) context).close();

	}    
}
