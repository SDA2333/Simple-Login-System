package com.cheng.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
//操作数据库
public class BassDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，用于初始化
    static{
       //获取db.properties中的值
        Properties properties = new Properties();
        InputStream is =BassDao.class.getClassLoader().getResourceAsStream("db.properties");
        //为了防止异常
        try {
            properties.load(is);
        }catch (IOException e){
            e.printStackTrace();//打印异常信息在程序中出错的位置及原因
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
//链接数据库
    public static Connection getConnection(){
        Connection connection =null;
        try {
            Class.forName(driver);//加载驱动
            connection = DriverManager.getConnection(url,username,password);//连接数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

//查询类
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws SQLException {

        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);//把要查询的参数对象传入预加载里，因为占位从1开始所以加一。
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }
//增删改类
    public static int execute(Connection connection,PreparedStatement preparedStatement, String sql, Object[] params ) throws SQLException {
        preparedStatement =connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
        preparedStatement.setObject(i+1,params[i]);//把要增删改的参数对象传入预加载里，因为占位从1开始所以加一。
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;//打标,有一个地方没有释放成功，就会成为false
        if(resultSet!=null) {
            try {
                resultSet.close();
                //GC回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement!=null) {
            try {
                preparedStatement.close();
                //GC回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if(connection!=null) {
            try {
                connection.close();
                //GC回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;//由flag来断定是否成功
    }


}
