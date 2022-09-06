package com.yoon.testkick.mockito.study;

import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void mock_메소드를_통해_만들기() {
        MemberService memberService = Mockito.mock(MemberService.class);
        StudyRepository studyRepository = Mockito.mock(StudyRepository.class);

        StudyService sut = new StudyService(memberService, studyRepository);

        assertNotNull(sut);
    }

    @Test
    void mock_애노테이션으로_만들기_필드() {
        StudyService sut = new StudyService(memberService, studyRepository);

        assertNotNull(sut);
    }

    @Test
    void mock_애노테이션으로_만들기_메소드_매개변수(@Mock MemberService memberService,
                                            @Mock StudyRepository studyRepository) {
        StudyService sut = new StudyService(memberService, studyRepository);

        assertNotNull(sut);
    }

    /** stubbing */
    @Test
    void stubbing_test_1() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@gmail.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> findById = memberService.findById(1L);
        assertEquals("jin@gmail.com", findById.get().getEmail());
    }

    @Test
    void stubbing_test_2() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@gmail.com");

        when(memberService.findById(any())).thenReturn(Optional.of(member));

        assertEquals("jin@gmail.com", memberService.findById(1L).get().getEmail());
        assertEquals("jin@gmail.com", memberService.findById(2L).get().getEmail());
    }

}