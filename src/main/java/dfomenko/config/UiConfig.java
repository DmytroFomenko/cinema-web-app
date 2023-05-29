package dfomenko.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties
public class UiConfig {

    private int additionNameMinLength;
    private int additionNameMaxLength;

    private String priceStep;
    private String priceMinValue;
    private String priceMaxValue;

    private String passwordMinLength;
    private String passwordMaxLength;

//    @Value("${password-reg-pattern}")
    private String passwordRegPattern;

//    @Value("${password-login-pattern}")
    private String passwordLoginPattern;

    private int nicknameMinLength;
    private int nicknameMaxLength;

//    @Value("${nickname-reg-pattern}")
    private String nicknameRegPattern;

//    @Value("${nickname-login-pattern}")
    private String nicknameLoginPattern;

//    @Value("${email-admin-pattern}")
    private String emailAdminPattern;

//    @Value("${email-client-pattern}")
    private String emailClientPattern;

//    @Value("session.client.timeout")
    private int sessionClientTimeout;

//    @Value("session.admin.timeout")
    private int sessionAdminTimeout;


}
