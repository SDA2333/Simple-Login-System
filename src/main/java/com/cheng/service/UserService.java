package com.cheng.service;

import com.cheng.pojo.user;

import java.sql.SQLException;

public interface UserService {
    public user login(String userCode, String password);
    public int register(String userCode, String password,String email);

    public int update(String userCode,String userName, String birthday,String phone) throws SQLException;
}
