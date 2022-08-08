package com.backbase.movies.service.impl;

import com.backbase.movies.domain.Role;
import com.backbase.movies.domain.User;
import com.backbase.movies.repository.RoleRepository;
import com.backbase.movies.repository.UserRepository;
import com.backbase.movies.service.RoleService;
import com.backbase.movies.service.dto.RoleDTO;
import com.backbase.movies.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;

    public RoleDTO saveRole(RoleDTO roleDTO) {
        log.info("saving new role {} to the database",roleDTO.getName());
        Role role = roleMapper.toEntity(roleDTO);
        roleRepository.save(role);
        return roleDTO;
    }

    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toDTO(roles);
    }

    public void addRoleToUser(String userName, String roleName) {
        log.info("adding role {} to user {} ",roleName,userName);
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

}
