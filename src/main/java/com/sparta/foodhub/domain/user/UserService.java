package com.sparta.foodhub.domain.user;

import com.sparta.foodhub.config.SecurityConfig;
//import com.sparta.foodhub.config.SecurityConfig.PasswordEncoder;
import com.sparta.foodhub.domain.user.dto.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 주입받은 PasswordEncoder

    public void signup(UserSignupRequest request) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.get비밀번호());

        // 사용자 저장
        User user = User.builder()
                .이름(request.get이름())
                .비밀번호(encodedPassword)
                .이메일(request.get이메일())
                .전화번호(request.get전화번호())
                .주소(request.get주소())
                .캐시잔액(0)
                .사용자유형(User.UserType.GUEST)
                .이메일인증여부(false)
                .build();

        userRepository.save(user);
    }
}
