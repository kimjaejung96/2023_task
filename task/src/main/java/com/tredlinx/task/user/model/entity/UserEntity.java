package com.tredlinx.task.user.model.entity;


import com.tredlinx.task.common.component.Encryptor;
import com.tredlinx.task.common.exception.CustomRuntimeException;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import com.tredlinx.task.common.model.enumurate.PointType;
import com.tredlinx.task.user.model.dto.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    public static UserEntity createUser(User.SignUp user) {
        return new UserEntity(user.getUserid(), user.getUsername(), user.getPw());
    }

    public void checkPw(String pw) {
        if (!Encryptor.encrypt(pw).equals(this.pw)) {
            throw new CustomRuntimeException(CustomApiCode.INVALID_PASSWORD);
        }
    }

    public UserEntity(String uid) {
        this.uid = uid;
    }

    public void addPoint(PointType type) {
        this.point += type.getPoint();
    }
}
