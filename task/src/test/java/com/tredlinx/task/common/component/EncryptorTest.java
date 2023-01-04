package com.tredlinx.task.common.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class EncryptorTest {
    @Test
    void 암호화_테스트()  {
        String result1 = Encryptor.encrypt("kimjaejung1234");
        String result2 = Encryptor.encrypt("kimjaejung1234");
        assertThat(result1, equalTo(result2));
    }
}
