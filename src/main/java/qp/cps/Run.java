package qp.cps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.silync", "qp.cps" })
@EnableCaching
@EnableScheduling
public class Run extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}

}
