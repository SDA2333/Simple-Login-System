package com.cheng.service;

import com.cheng.dao.BassDao;
import com.cheng.dao.user.UserDao;
import com.cheng.dao.user.UserDaoImpl;
import com.cheng.pojo.user;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


    public  class UserServicelmpl implements UserService {

        //接口引用实现类。
        private UserDao userDao;
        public UserServicelmpl(){
            userDao = new UserDaoImpl();
        }



        //用户查询
        @Override
        public user login(String userCode, String password){


            Connection connection =null;
            user user = null;
            try {
                //建立数据库连接。
                connection = BassDao.getConnection();
                //调用dao层方法，获得登录对象user
                user = userDao.getLoginUser(connection, userCode);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
                BassDao.closeResource(connection,null,null);
            }
            return user;
        }



        //用户注册
        public int register(String userCode, String password,String email){


            Connection connection =null;
            int rs=0;
            try {
                //建立数据库连接。
                connection = BassDao.getConnection();

                //调用dao层方法，实现注册。
                rs = userDao.getRegisterUser(connection,userCode,password,email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                BassDao.closeResource(connection,null,null);
            }
            return rs;
        }
        //用户信息修改
        @Override
        public int update(String userCode, String userName, String birthday, String phone) throws SQLException {
            Connection connection=null;
            int rs=0;
            //执行修改

            try {
                //建立数据库连接。
                connection = BassDao.getConnection();

                //调用dao层方法，实现个人信息修改
                rs = userDao.getUpdateUser(connection,userCode,userName,birthday,phone);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
                BassDao.closeResource(connection,null,null);
            }

            return rs;
        }




    }
