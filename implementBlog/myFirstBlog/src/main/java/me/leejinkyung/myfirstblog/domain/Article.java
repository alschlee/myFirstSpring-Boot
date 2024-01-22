package me.leejinkyung.myfirstblog.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id // id 필드를 pk로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk가 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // builder 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // builder 패턴 사용 x 때
    // protected Article() {}
    // public Long getId() { return id; }
    // public String getTitle() { return title; }
    // public String getContent() { return content; }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
