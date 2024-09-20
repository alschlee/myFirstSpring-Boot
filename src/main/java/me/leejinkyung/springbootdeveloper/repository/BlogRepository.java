package me.leejinkyung.springbootdeveloper.repository;

import me.leejinkyung.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
