package me.leejinkyung.myfirstblog.repository;

import me.leejinkyung.myfirstblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
