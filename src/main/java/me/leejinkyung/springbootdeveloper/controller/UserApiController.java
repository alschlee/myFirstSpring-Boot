package me.leejinkyung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.leejinkyung.springbootdeveloper.dto.AddUserRequest;
import me.leejinkyung.springbootdeveloper.service.UserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@ControllerAdvice
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login"; // 회원가입 완료된 이후에 로그인 페이지로 이동
    }
}
