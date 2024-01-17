package me.leejinkyung.springbootdeveloper;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

// 퍼시스턴트 계층: 데이터베이스 관련 로직 처리
// JpaRespository<Member, Long> ~> 기본적인 CRUD 기능 제공하는 인터페이스 JpaRepository 상속받고 있음, 'Member' 엔터티와 'Long' 타입의 기본키 사용
// 즉, 'MemberRepository' 통해 'Member' 엔터티에 대한 데이터베이스 조작 가능
// 'JpaRepository'는 데이터베이스에 데이터를 저장, 조회, 수정, 삭제 등 메서드 제공하고, 이를 통해 편리하게 DB 액세스
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
}
