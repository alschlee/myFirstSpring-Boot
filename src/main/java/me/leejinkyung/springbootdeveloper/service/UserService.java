package me.leejinkyung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.leejinkyung.springbootdeveloper.domain.User;
import me.leejinkyung.springbootdeveloper.dto.AddUserRequest;
import me.leejinkyung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User .builder()
                .email(dto.getEmail())
                // password 암호화
                .password(bCryptPasswordEncoder .encode(dto.getPassword()))
                .build()).getId();
    }
}
