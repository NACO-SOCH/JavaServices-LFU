package gov.naco.soch.lfu;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"gov.naco.soch.lfu.lfurepository"})
@EnableAsync
@EntityScan(basePackages={"gov.naco.soch.lfu.domains"})
@ComponentScan(basePackages = { "gov.naco.soch" })
//@PropertySource("classpath:application-${spring.profiles.active}.yml")
public class LfuServiceApplication extends SpringBootServletInitializer {

	public static final String TIMEZONE_IST = "Asia/Kolkata";
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LfuServiceApplication.class);
	}

	/**
	 * Main method, used to run the application.
	 *
	 * @param args
	 *            the command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LfuServiceApplication.class);
	}

	/*
	 * created_time, modified_time time values should be saved in UTC timezone
	 * values, using '@CreatedDate' and '@LastModifiedDate' in Auditable class.
	 */
	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_IST));
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
