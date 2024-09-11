package org.example.expert.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class PasswordEncoderTest {

    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @Test
    void matches() {
        // given
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // when 비밀번호 비교는 passwordEncoder.matches(비밀번호, 암호화된 비밀번호) 를 이용해야합니다.
        // matches 는 암호화된 password 에서 salt 값을 추출, 원본에 salt 값을 이용해 해싱, 그 값으로 비교.
        boolean matches = passwordEncoder.matches(rawPassword,encodedPassword);

        // assertTrue 안에 값이 True 이면 예상이 true 값도 true 문제 없음.
        assertTrue(matches);
    }
}
