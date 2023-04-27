package fdv.service.impl;


import fdv.entity.LoginData;
import fdv.entity.Role;
import fdv.repository.LoginDataRepository;
import fdv.repository.RoleRepository;
import fdv.service.FilmService;
import fdv.service.LoginDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginDataServiceImpl implements LoginDataService {

    private LoginDataRepository loginDataRepository;
    private RoleRepository roleRepository;

    @Override
    public List<LoginData> getAllLoginData() {
        return loginDataRepository.findAll();
    }

    @Override
    public LoginData getLoginDataById(Long id) {
        return loginDataRepository.findById(id).get();
    }

    @Override
    public boolean checkLoginData(LoginData loginData) {
        LoginData foundLoginData = loginDataRepository.findLoginDataByNickname(loginData.getNickname());
        if (foundLoginData != null) {
            return foundLoginData.getPassword().equals(loginData.getPassword());
        }
        return false;
    }

    @Override
    public boolean existsLoginDataByNickname(String nickname) {
        return loginDataRepository.existsLoginDataByNickname(nickname);
    }

    @Override
    public LoginData findLoginDataByNickname(String nickname) {
        return loginDataRepository.findLoginDataByNickname(nickname);
    }

    @Override
    public LoginData saveLoginData(LoginData loginData) {
        return loginDataRepository.save(loginData);
    }

    @Override
    public LoginData findLoginDataById(Long loginDataId) {
        return loginDataRepository.findLoginDataById(loginDataId);
    }
}
