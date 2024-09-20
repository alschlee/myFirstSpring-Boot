package me.leejinkyung.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
}
