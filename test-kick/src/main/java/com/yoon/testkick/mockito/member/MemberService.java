package com.yoon.testkick.mockito.member;

import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
    void save(Member member);
    void validate(Long MemberId);
    void notify(Study study);
    void notify(Member member);
}