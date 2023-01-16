package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}