package me.leejinkyung.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public void test() {
        // 생성 (Create)
        memberRepository.save(new Member(1L, "A")); // save() 호출해 데이터 객체 저장

        // 조회 (Read)
        Optional<Member> member = memberRepository.findById(1L);
        List<Member> allMembers = memberRepository.findAll(); // 전체 조회

        // 삭제 (Delete)
        memberRepository.deleteById(1L);
    }
}
