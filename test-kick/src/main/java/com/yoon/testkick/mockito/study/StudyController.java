package com.yoon.testkick.mockito.study;

import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.domain.Study;
import com.yoon.testkick.mockito.domain.StudyStatus;
import com.yoon.testkick.mockito.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class StudyController {

    final StudyRepository repository;
    final MemberRepository memberRepository;

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        memberRepository.save(new Member(id, "jin@mail.com"));
        createsStudy(new Study(id, StudyStatus.STARTED, 10, "짱짱맨",  LocalDateTime.now().plusDays(4), id));
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createsStudy(@RequestBody Study study) {
        return repository.save(study);
    }

}