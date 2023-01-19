package com.yoon.boardingExpress.repository;

import com.yoon.boardingExpress.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WriterRepository extends JpaRepository<Writer, Long> {
}