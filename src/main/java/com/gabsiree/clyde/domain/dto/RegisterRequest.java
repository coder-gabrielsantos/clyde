package com.gabsiree.clyde.domain.dto;

import com.gabsiree.clyde.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}
