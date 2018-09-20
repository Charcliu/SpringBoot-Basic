package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public void addUserInfo(User user);
}
