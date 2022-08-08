package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.Role;
import com.backbase.movies.service.dto.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    List<RoleDTO> toDTO(List<Role> roles);
}
