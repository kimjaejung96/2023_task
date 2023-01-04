package com.tredlinx.task.user.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class signUp {
        private String userId;
        private String pw;
        private String username;

        public signUp(String userId, String pw, String username) {
            this.userId = userId;
            this.pw = pw;
            this.username = username;
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class signIn {
        private String userId;
        private String pw;
    }

}
