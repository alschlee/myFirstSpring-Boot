package me.leejinkyung.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Member 테이블 구조 정의, JPA 통해 객체와 데이터베이스 간의 매핑 수행
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor
@Getter
@Entity // 이 클래스가 JPA 엔터티임을 나타냄. DB의 테이블과 매핑되는 객체라는 걸 JPA에게 알려줌
public class Member {
    @Id // 이 필드가 DB의 기본키(primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 방식 결정 ~ 자동 증가. DB의 identity 컬럼 이용해 pk 생성
    @Column(name = "id", updatable = false) // DB의 칼럼과 매핑되는 필드 지정. 업데이트 연산에서 이 필드는 무시
    private Long id;

    @Column(name = "name", nullable = false) // null X
    private String name;
}
