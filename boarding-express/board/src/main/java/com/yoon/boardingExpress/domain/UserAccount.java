package com.yoon.boardingExpress.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "phoneNumber"),
        @Index(columnList = "email", unique = true)
})
@Entity
public class UserAccount extends AuditingFields {

    @Id
    @Column(length = 50)
    private String id;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false, length = 100)
    private String email;

    @Setter
    @Column(nullable = false, length = 200)
    private String phoneNumber;

    @Setter
    @Column
    private String memo;

    @ToString.Exclude
    @OrderBy("userAccount.id")
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private final Set<Article> articles = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("userAccount.id")
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    protected UserAccount() {
    }

    public static UserAccount of(String userId, String userPassword, String email, String name, String phoneNumber, String memo) {
        return new UserAccount(userId, userPassword, email, name, phoneNumber, memo);
    }

    private UserAccount(String id, String password, String name, String email, String phoneNumber, String memo) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount userAccount = (UserAccount) o;
        return id != null && id.equals(userAccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
