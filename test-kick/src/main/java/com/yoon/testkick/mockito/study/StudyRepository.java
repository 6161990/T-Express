package com.yoon.testkick.mockito.study;

import com.yoon.testkick.mockito.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
