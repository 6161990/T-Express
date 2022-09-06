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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockingStudyTest {

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

    @Test
    void stubbing_test_4_with_throw() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@gmail.com");

        when(memberService.findById(1L)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, ()-> {
            memberService.findById(1L);
        });
    }

    @Test
    void stubbing_test_5_with_throw_void_method() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@gmail.com");

        doThrow(new RuntimeException()).when(memberService).validate(1L);

        assertThrows(RuntimeException.class, ()-> {
            memberService.validate(1L);
        });

        memberService.validate(2L);
    }

    @Test
    void stubbing_test_6_with_many_times() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@gmail.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("jin@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, ()-> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));
    }
}