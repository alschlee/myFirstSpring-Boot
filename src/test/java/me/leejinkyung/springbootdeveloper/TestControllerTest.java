package me.leejinkyung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 메인 어플리케이션 클래스에 추가하는 애너테이션 @SpringBootApplication 이 있는 클래스를 찾고, 그 클래스에 포함되어 있는 빈 찾고, 테스트용 어플리케이션 컨텍스트를 만든다.
@AutoConfigureMockMvc // MockMvc(서버 배포 안해도 테스트용 MVC 환경 만들어 요청 및 전송, 응답 기능 제공) 생성 및 자동 구성
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다")
    @Test
    public void getAllMembers() throws Exception {
        // given: 멤버 저장
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "김정우"));

        // when: 멤버 리스트 조회 API 호출
        final ResultActions result = mockMvc.perform(get(url) // perform(): 요청 전송하는 역할, 결과로 ResultActions 객체 받는다.
                .accept(MediaType.APPLICATION_JSON)); // 요청 보낼 때 무슨 타입으로 응답받을지 결정 -> JSON으로 받는다

        // then: 응답 코드 200OK이고, 반환 받은 값 중 0번째 요소의 id와 name이 저장된 값과 같은지 확인
        result.andExpect(status().isOk()) // 응답 검증: 응답 코드가 OK(200)인지 확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId())) // JSON 응답값의 값을 가져온다
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));

    }
}