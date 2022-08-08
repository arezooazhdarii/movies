package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.User;
import com.backbase.movies.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
