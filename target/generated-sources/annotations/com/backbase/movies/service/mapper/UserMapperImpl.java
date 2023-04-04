package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.Role;
import com.backbase.movies.domain.User;
import com.backbase.movies.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-04T12:53:54+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 19.0.2 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        Collection<Role> collection = user.getRoles();
        if ( collection != null ) {
            userDTO.setRoles( new ArrayList<Role>( collection ) );
        }

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        Collection<Role> collection = userDTO.getRoles();
        if ( collection != null ) {
            user.setRoles( new ArrayList<Role>( collection ) );
        }

        return user;
    }
}
