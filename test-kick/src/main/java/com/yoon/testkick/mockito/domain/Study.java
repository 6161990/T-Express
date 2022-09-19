package com.yoon.testkick.mockito.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Study {

    @Id //@GeneratedValue
    private Long id;
    private StudyStatus status = StudyStatus.DRAFT;
    private int limitCount;
    private String name;
    private LocalDateTime openedDateTime;
    private Long ownerId;

    public Study(int limit, String name) {
        this.limitCount = limit;
        this.name = name;
    }

    public Study(Long id, StudyStatus status, int limitCount, String name, LocalDateTime openedDateTime, Long ownerId) {
        this.id = id;
        this.status = status;
        this.limitCount = limitCount;
        this.name = name;
        this.openedDateTime = openedDateTime;
        this.ownerId = ownerId;
    }

    public void open() {
        publish();
    }

    private void publish() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }

}
