package fdv.service;


import fdv.entity.LoginData;

import java.util.List;

public interface LoginDataService {
    List<LoginData> getAllLoginData();

    LoginData getLoginDataById(Long id);

    boolean checkLoginData(LoginData loginData);

    boolean existsLoginDataByNickname(String nickname);

    LoginData findLoginDataByNickname(String nickname);

    LoginData saveLoginData(LoginData loginData);
    LoginData findLoginDataById(Long loginDataId);

}
