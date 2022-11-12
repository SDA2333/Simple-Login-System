


 //dao层，与数据库进行交互。主要通过调用basedao层的增删改查，来具体实现某一功能。具体实现了用户的查询，注册

package com.cheng.dao.user;
import com.cheng.dao.BassDao;
import com.cheng.pojo.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    //得到登录对象
    public user getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet re = null;
        user user = null;

        //数据库在service层连接的，如果连接成功。
        if (connection != null) {

            //编写sql语句，params是因为要预处理，占位要传的参。
            String sql = "select * from user where usercode=?";
            Object[] params = {userCode};

            //调用bassdao方法，查询数据，返回结果集。
            try {
                re = BassDao.execute(connection, pstm, re, sql, params);

                while (re.next()) {
                    //将结果集里的值交给user对象。
                    user = new user();
                    user.setId(re.getInt("id"));
                    user.setUserCode(re.getString("userCode"));
                    user.setUserName(re.getString("userName"));
                    user.setUserPassword(re.getString("userPassword"));
                    user.setGender(re.getInt("gender"));
                    user.setBirthday(re.getDate("birthday"));
                    user.setPhone(re.getString("phone"));
                    BassDao.closeResource(null, pstm, re);
                }
            } catch (SQLException e) {
                e.printStackTrace();//打印错误内容
            }


        }


        return user;
    }

    //用户注册
    public int getRegisterUser(Connection connection, String userCode, String userPassword,String userEmail) throws SQLException {


        ResultSet rs = null;
        PreparedStatement pstm = null;
        int rs1 = 0;
        user user = null;

        if (connection != null) {

            //编写sql语句，params是因为要预处理
            String sql = "insert into user (userCode,userPassword,email) values (?,?,?)";
            Object[] params = {userCode,userPassword,userEmail};

            //调用basedao方法，查询数据，返回结果。
            rs1 = BassDao.execute(connection, pstm,sql, params);

            BassDao.closeResource(null, pstm, rs);

        }

        return rs1;
    }
    //用户个人信息修改
    public int getUpdateUser(Connection connection, String userCode,String userName,String birthday,String phone) throws SQLException{


        ResultSet rs = null;
        PreparedStatement pstm = null;
        int rs1 = 0;
        user user = null;

        //连接成功
        if (connection != null) {

            //编写sql语句，params是因为要预处理
            String sql = "update xin.user set userName=?,birthday=?,phone=? where userCode=?";
            Object[] params = {userName,birthday,phone,userCode};

            //调用basedao方法，查询数据，返回结果。
            rs1 = BassDao.execute(connection, pstm,sql, params);

            //关闭
            BassDao.closeResource(null, pstm, rs);

        }

        return rs1;

    }

}
