package me.leejinkyung.myfirstblog.controller;

import lombok.RequiredArgsConstructor;
import me.leejinkyung.myfirstblog.domain.Article;
import me.leejinkyung.myfirstblog.dto.AddArticleRequest;
import me.leejinkyung.myfirstblog.dto.ArticleResponse;
import me.leejinkyung.myfirstblog.dto.UpdateArticleRequest;
import me.leejinkyung.myfirstblog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class
BlogApiController {

    private final BlogService blogService;

    // 글 작성
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성됨, 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // 전체 글 조회하고 반환
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream() // 추가된 컬렉션의 저장 요소 하나씩 참조해 람다식으로 처리 ~ 스트림 생성
                .map(ArticleResponse::new) // ArticleResponse 클래스 생성자 이용해 각 게시물을 ArticleResponse의 객체로 변환
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    // 글 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { // @PathVariable: URL에서 값을 가져오는 애너테이션 ~ /api/articles/3 get 요청오면 id에 3이 들어온다
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article)); // ex) 3번 글의 정보를 body에 담아 웹 브라우저로 전송
    }

    // 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
