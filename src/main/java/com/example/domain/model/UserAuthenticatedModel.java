package com.example.domain.model;


import com.example.domain.enums.UserRolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticatedModel {
    private String email;
    private UserRolEnum role;
}
