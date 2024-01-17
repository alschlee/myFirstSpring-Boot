package me.leejinkyung.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 프레젠테이션 계층: HTTP 요청을 받고 비즈니스 계층(TestService)으로 전송
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMember(){
        List<Member> members = testService.getAllMembers();
        return members;
    }
}
