package com.sparta.foodhub.domain.user;

import com.sparta.foodhub.domain.user.dto.UserSignupRequest;
import com.sparta.foodhub.util.AesEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository; // Mock 객체로 대체
    @Mock
    private PasswordEncoder passwordEncoder; // Mock 객체로 대체

    @InjectMocks
    private UserService userService; // 테스트 대상

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }

    @Test
    void signup_ShouldEncryptPasswordAndPersonalInfo() {
        // Arrange: 테스트 데이터 생성
        UserSignupRequest request = UserSignupRequest.builder()
                .이름("홍길동")
                .비밀번호("password123")
                .이메일("hong@gmail.com")
                .전화번호("010-1234-5678")
                .주소("서울시 강남구")
                .build();

        // Mock 동작 정의
        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(null); // 저장 동작 Mock

        // Act: 메서드 실행
        userService.signup(request);

        // Assert: 저장된 데이터 검증
        verify(userRepository, times(1)).save(argThat(user -> {
            // 비밀번호가 암호화되었는지 확인
            assertEquals("encryptedPassword", user.get비밀번호());

            // 개인정보가 암호화되었는지 확인
            assertEquals(AesEncryptor.encrypt("홍길동"), user.get이름());
            assertEquals(AesEncryptor.encrypt("서울시 강남구"), user.get주소());

            return true;
        }));
    }

}