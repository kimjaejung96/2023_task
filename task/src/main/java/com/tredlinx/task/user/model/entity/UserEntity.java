package com.tredlinx.task.user.model.entity;


import com.tredlinx.task.user.model.dto.User;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name = "USER_ID", columnDefinition = "varchar(20)")
    private String userId;

    @Column(name = "USER_NAME", columnDefinition = "varchar(10)")
    private String userName;

    @Column(name = "PASSWORD", columnDefinition = "varchar(64)")
    private String password;

    @Column(name = "POINT", columnDefinition = "bigint(10)")
    private long point;

    public UserEntity(String userId, String userName, String pw) {
        this.uid = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.password = pw;
        this.point = 0;
    }

    public static UserEntity createUser(User user) {
        return new UserEntity(user.getUserId(), user.getUsername(), user.getPw());
    }
}
