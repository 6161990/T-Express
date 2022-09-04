package com.yoon.testkick.mockito.study;

import com.yoon.testkick.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
}