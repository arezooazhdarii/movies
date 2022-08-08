package com.backbase.movies.service.dto;

import com.backbase.movies.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<Role> roles;
}
