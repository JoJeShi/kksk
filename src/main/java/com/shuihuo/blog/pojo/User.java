package com.shuihuo.blog.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.net.URL;

@Document(collection = "user")

public class User {
    @Id
    private String userID;

    @Field(value = "username")
    private  String username;

    @Field(value="password")
    private String password;
    @Field(value = "mailadr")
    private String mailAdress;
    @Field(value = "picture")
    private String picture;
    @Field(value = "age")
    private Integer age;

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserID() {
        return userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString(){
        return "User [id="+userID+", mane="+username+", age="+age+", mailAdress="+mailAdress+" ]";
    }
}
