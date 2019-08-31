package com.shuihuo.blog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "blogUser")
public class User {
    private String username;
    private String password;
    private String mailAddress;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
