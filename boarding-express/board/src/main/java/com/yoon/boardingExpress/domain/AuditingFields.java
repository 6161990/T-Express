package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {
    /** 공통 필드를 관리하는 두 가지 방법(선호도에 따라 공통 필드가 아니라 중복이더라고 1:1 매핑으로 두는 경우도 있다)
     * 1. @Embedded 클래스를 만들면 여기 클래스안에 정의된 필드가 해당 클래스의 필드로 테이블에 들어가게 된다.
     * 2. @MappedSuperclass  */

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
