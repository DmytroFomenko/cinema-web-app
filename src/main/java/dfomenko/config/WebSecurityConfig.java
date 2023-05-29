package dfomenko.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import static dfomenko.controller.LoginDataController.ADMIN_ROLE_NAME;
import static dfomenko.controller.LoginDataController.CLIENT_ROLE_NAME;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .invalidSessionUrl("/") // TODO: 29.05.2023 Добавить странцу с сообщением клиенту об отмене его броней по таймауту
                        .sessionAuthenticationErrorUrl("/session_time_expired"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/create**",
                                         "/update**",
                                         "/delete**",
                                         "/show**",
                                         "/bill**",
                                         "/films**",
                                         "/additions**",
                                         "/placeKinds**",
                                         "/placements**",
                                         "/places**").hasRole(ADMIN_ROLE_NAME)
                        .requestMatchers("/buy**",
                                         "/client**").hasRole(CLIENT_ROLE_NAME)
                        .requestMatchers("/",
                                         "/login**",
                                         "/logout**",
                                         "/signup**",
                                         "/more_info**",
                                         "/recover**",
                                         "/cinemafiles/**").permitAll()
                        .anyRequest().authenticated())
                .logout((logout) -> logout.logoutUrl("/logout_user**")
                                          .logoutSuccessUrl("/"))
        ;

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .debug(true);
    }
    
}