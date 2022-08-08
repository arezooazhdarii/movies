package com.backbase.movies.service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class RoleToUserDTO {
    private String userName;
    private String roleName;
}
