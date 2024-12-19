package com.sparta.foodhub.domain.user;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "사용자")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long 아이디;

    @Column(nullable = false)
    private String 이름;

    @Column(nullable = false)
    private String 비밀번호;

    @Column(nullable = false, unique = true)
    private String 이메일;

    @Column(nullable = false)
    private String 전화번호;

    @Column(nullable = false)
    private String 주소;

    @Column(nullable = false)
    private int 캐시잔액;

    @Enumerated(EnumType.STRING)
    private UserType 사용자유형;

    private boolean 이메일인증여부;

    @Column(nullable = false, updatable = false)
    private LocalDateTime 생성일시;

    @PrePersist
    protected void onCreate() {
        생성일시 = LocalDateTime.now();
    }

    public enum UserType {
        GUEST, ADMIN
    }
}
