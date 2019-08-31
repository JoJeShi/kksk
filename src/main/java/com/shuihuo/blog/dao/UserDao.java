package com.shuihuo.blog.dao;

import com.shuihuo.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface UserDao extends MongoRepository<User,Integer> {
    User findUserByMailAddressAndPassword(String mailAddress,String password);
    User findUserByMailAddress(String mailAddress);
    ArrayList<User> findUserByUsername(String username);
}
