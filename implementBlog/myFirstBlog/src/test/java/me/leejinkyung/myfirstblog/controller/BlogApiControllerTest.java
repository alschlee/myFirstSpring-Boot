package me.leejinkyung.myfirstblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.leejinkyung.myfirstblog.domain.Article;
import me.leejinkyung.myfirstblog.dto.AddArticleRequest;
import me.leejinkyung.myfirstblog.dto.UpdateArticleRequest;
import me.leejinkyung.myfirstblog.repository.BlogRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 자바 객체를 JSON 데이터로 변환 (직렬화) or JSON 데이터를 자바에서 사용하기 위해 자바 객체로 변환(역직렬화)

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    // 글 작성
    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {

        //given: 블로그 글 추가에 필요한 요청 객체 만들기
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when: 블로그 글 추가 API에 요청 보내기 (요청타입: JSON, given 절에서 미리 만들어 둔 객체를 요청본문으로 함께 보냄)
        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));

        // then: 응답코드가 201 Created인지 확인, Blog 전체 조회해 크기가 1인지 확인, 실제로 저장된 데이터와 요청 값 비교
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    // 글 목록 조회
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception {
        // given: 블로그 글 저장하기
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder().title(title).content(content).build());

        // when: 목록 조회 API 호출하기
        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then: 응답코드가 200 OK이고, 반환받은 값 중 0번째 요소의 content와 title이 저장된 값이 같은지 확인
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    // 글 조회
    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception {
        // given: 블로그 글 저장하기
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when: 저장한 블로그 글의 id값으로 API 호출하기
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        // then: 응답코드가 200 OK이고, 반환받은 값 중 0번째 요소의 content와 title이 저장된 값이 같은지 확인
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    // 글 삭제
    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        // given: 블로그 글 저장하기
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when: 저장한 블로그의 id 값으로 삭제 API 호출하기
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(status().isOk());

        // then: 응답 코드가 200 OK이고, 블로그 글 리스트를 전체 조회해 조회한 배열 크기가 0인지 확인
        List<Article> articles = blogRepository.findAll();

        assertThat(articles).isEmpty();
    }

    // 글 수정
    @DisplayName("updateArticle: 블로그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception {
        // given: 블로그 글을 저장하고, 글 수정에 필요한 요청 객체 만들기
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

        // when: UPDATE API로 수정 요청 보내기 (요청 타입: JSON, given 절에서 미리 만들어 둔 객체를 요청 본문으로 함께 보냄)
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then: 응답코드가 200 OK인지 확인하고, 블로그 글 id로 조회한 후 값이 수정되었는지 확인
        result.andExpect(status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}