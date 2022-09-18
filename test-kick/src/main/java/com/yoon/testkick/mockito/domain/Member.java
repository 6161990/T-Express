package com.yoon.testkick.mockito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id //@GeneratedValue
    private Long id;

    private String email;

    public Member(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}