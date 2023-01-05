package com.tredlinx.task.user.repository;

import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Optional;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;


    @Test
    void 회원가입() {

        UserEntity userEntity = UserEntity.createUser(new User.SignUp("kimjaejungh", "kimjaejungz", "김재중"));
        userRepo.save(userEntity);

        Optional<UserEntity> findUser = userRepo.findById(userEntity.getUid());
        assertThat(findUser.get().getUserName(), is("김재중"));
    }
}
