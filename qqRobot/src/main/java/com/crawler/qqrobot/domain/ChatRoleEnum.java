package com.crawler.qqrobot.domain;

public enum ChatRoleEnum {
    USER("user"),ASSISTANT("assistant");
    private String role;

    private ChatRoleEnum(String user) {
        role = user;
    }

    public String getRole() {
        return role;
    }
}
