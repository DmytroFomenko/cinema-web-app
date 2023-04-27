package fdv.service;


import fdv.entity.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role findRoleByRole(String role);
}
