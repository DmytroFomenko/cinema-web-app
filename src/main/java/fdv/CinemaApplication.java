package fdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// TODO: 15.04.2023  для удаления стандартной страницы логина добавил (exclude = { SecurityAutoConfiguration.class })

//(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
//

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

}
