package com.cheng.servlet.user;

import com.cheng.pojo.user;
import com.cheng.service.UserService;
import com.cheng.service.UserServicelmpl;
import com.cheng.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cheng.service.UserService.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取用户代码和密码
        String userCode = req.getParameter("userCode");
        String userPassword= req.getParameter("userPassword");
        //对比
        UserServicelmpl userServicelmpl = new UserServicelmpl();
        user user;

        user = userServicelmpl.login(userCode,userPassword);
        if (user!=null&&userPassword.equals(user.getUserPassword())){
            //判断密码是否正确
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            req.getSession().setAttribute(Constants.USER_ID,user.getId());
            req.getSession().setAttribute(Constants.USER_CODE,user.getUserCode());
            req.getSession().setAttribute(Constants.USER_NAME,user.getUserName());
            req.getSession().setAttribute(Constants.USER_PASSWORD,user.getUserPassword());
            req.getSession().setAttribute(Constants.USER_GENDER,user.getGender());
            req.getSession().setAttribute(Constants.USER_BIRTHDAY,user.getBirthday());
            req.getSession().setAttribute(Constants.USER_PHONE,user.getPhone());
            req.getSession().setAttribute(Constants.USER_EMAIL,user.getEmail());//设置SESSION
            resp.sendRedirect("personal page.jsp");

        }else {
            req.setAttribute("error","错了");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }






    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
