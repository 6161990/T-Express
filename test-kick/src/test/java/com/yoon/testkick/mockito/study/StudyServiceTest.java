package com.yoon.testkick.mockito.study;


import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.domain.Study;
import com.yoon.testkick.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository repository;

    @Test
    void stubbing_practice() {
        StudyService studyService = new StudyService(memberService, repository);

        Member member = new Member();
        member.setId(1L);
        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(repository.save(study)).thenReturn(study);

        Study resultStudy = studyService.createNewStudy(1L, study);

        assertNotNull(resultStudy.getOwnerId());
        assertThat(resultStudy.getOwnerId()).isEqualTo(member.getId());
    }
}
