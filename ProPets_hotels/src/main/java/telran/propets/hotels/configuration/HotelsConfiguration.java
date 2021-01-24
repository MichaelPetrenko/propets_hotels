package telran.propets.hotels.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HotelsConfiguration {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	};

}
