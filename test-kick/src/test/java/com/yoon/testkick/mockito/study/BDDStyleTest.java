package com.yoon.testkick.mockito.study;

import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.domain.Study;
import com.yoon.testkick.mockito.domain.StudyStatus;
import com.yoon.testkick.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BDDStyleTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createNewStudy() {
        StudyService sut = new StudyService(memberService, studyRepository);
        assertNotNull(sut);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@mail.com");

        Study study = new Study(10, "테스트");

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        sut.createNewStudy(member.getId(), study);

        assertEquals(member.getId(), study.getOwnerId());
        then(memberService).should().notify(study);
        then(memberService).should().notify(member);
        then(memberService).shouldHaveNoMoreInteractions();
    }

    @Test
    void openStudy() {
        StudyService sut = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "java");
        assertNull(study.getOpenedDateTime());
        given(studyRepository.save(study)).willReturn(study);

        sut.openStudy(study);

        assertNotNull(study.getOpenedDateTime());
        assertThat(study.getStatus()).isEqualTo(StudyStatus.OPENED);
        then(memberService).should(times(1)).notify(study);
    }
}
