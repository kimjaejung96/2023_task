package com.tredlinx.task.user.model.entity;


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

    @Builder(builderMethodName = "createUser")
    public UserEntity(String userId, String userName, String password) {
        this.uid = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
