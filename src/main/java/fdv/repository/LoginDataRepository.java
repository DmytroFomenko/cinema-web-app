package fdv.repository;


import fdv.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData, Long> {
    LoginData findLoginDataByNickname(String nickname);

    boolean existsLoginDataByNickname(String nickname);

    LoginData findLoginDataById(Long loginDataId);
}
