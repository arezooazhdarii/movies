package com.backbase.movies.service;

import com.backbase.movies.service.dto.RoleDTO;
import java.util.List;

public interface RoleService {
    RoleDTO saveRole(RoleDTO roleDTO);
    List<RoleDTO> getRoles();
    void addRoleToUser(String userName, String roleName);
}
