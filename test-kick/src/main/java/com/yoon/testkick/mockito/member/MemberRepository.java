package com.yoon.testkick.mockito.member;

import com.yoon.testkick.mockito.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
