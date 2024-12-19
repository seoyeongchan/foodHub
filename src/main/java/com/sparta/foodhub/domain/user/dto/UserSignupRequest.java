package com.sparta.foodhub.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter @Setter
public class UserSignupRequest {
    private String 이름;
    private String 비밀번호;
    private String 이메일;
    private String 전화번호;
    private String 주소;


}