package arom_semo.server.login.repository;

import arom_semo.server.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //중복 존재하는지
    Boolean existsByUsername(String username);

    //username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    Member findByUsername(String username);
}

