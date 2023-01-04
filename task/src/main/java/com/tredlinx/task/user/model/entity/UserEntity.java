package com.tredlinx.task.user.model.entity;


import com.tredlinx.task.common.component.Encryptor;
import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;
import com.tredlinx.task.user.model.dto.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Table(name = "USER_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @Column(name = "UID", columnDefinition = "char(36)")
    private String uid;

    @Column(name = "USER_ID", columnDefinition = "VARCHAR(20)")
    private String userId;

    @Column(name = "USER_NAME", columnDefinition = "VARCHAR(10)")
    private String userName;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(64)")
    private String pw;

    @Column(name = "POINT", columnDefinition = "BIGINT(5)")
    private Long point;

    public UserEntity(String userId, String userName, String pw) {
        this.uid = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.pw = Encryptor.encrypt(pw);
        this.point = 0L;
    }

    public static UserEntity createUser(User.signUp user) {
        return new UserEntity(user.getUserId(), user.getUsername(), user.getPw());
    }

    public void checkPw(String pw) throws CustomException {
        if (!Encryptor.encrypt(pw).equals(this.pw)) {
            throw new CustomException(ApiExceptionCode.INVALID_PASSWORD);
        }
    }
}
