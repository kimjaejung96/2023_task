package com.tredlinx.task.user.model.dto;

import com.tredlinx.task.user.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class signUp {
        private String userid;
        private String pw;
        private String username;

        public signUp(String userid, String pw, String username) {
            this.userid = userid;
            this.pw = pw;
            this.username = username;
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class signIn {
        private String userid;
        private String pw;
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class profile {
        private String userid;
        private String username;
        private Long point;

        public profile(UserEntity user) {
            this.userid = user.getUserId();
            this.username = user.getUserName();
            this.point = user.getPoint();
        }
    }

}
