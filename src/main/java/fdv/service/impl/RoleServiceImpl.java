package fdv.service.impl;


import fdv.entity.Role;
import fdv.repository.RoleRepository;
import fdv.service.LoginDataService;
import fdv.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;


    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }
}
