package com.yoon.testkick.mockito.member;

import com.yoon.testkick.mockito.domain.Member;

public interface MemberService {
    void validate(Long memberId) throws InvalidMemberException;

    Member findById(Long memberId) throws MemberNotFoundException;
}