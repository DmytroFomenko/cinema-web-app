package fdv.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import fdv.repository.RoleRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login_data")
public class LoginData {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Long roleId;


    public LoginData() {
    }



}
