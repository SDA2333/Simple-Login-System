

//dao层，与数据库进行交互。主要通过调用basedao层的增删改查，来具体实现某一功能。


package com.cheng.dao.user;

import com.cheng.pojo.user;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public user getLoginUser(Connection connection, String userCode) throws SQLException;
    public int getRegisterUser(Connection connection, String userCode,String userPassword,String userEmail) throws SQLException;

    public int getUpdateUser(Connection connection, String userCode,String userName,String birthday,String phone) throws SQLException;
}

