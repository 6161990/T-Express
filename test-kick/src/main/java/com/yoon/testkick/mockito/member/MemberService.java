package com.yoon.testkick.mockito.member;

import com.yoon.testkick.mockito.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
    void validate(Long MemberId);
}