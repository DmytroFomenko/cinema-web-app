package dfomenko;

import dfomenko.config.UiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.SessionAttributes;


// TODO: 15.04.2023  для удаления стандартной страницы логина добавил (exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
@SessionAttributes({"loggedUser"})
@EnableConfigurationProperties(UiConfig.class)
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

}
