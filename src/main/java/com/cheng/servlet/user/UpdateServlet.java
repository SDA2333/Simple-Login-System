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
import java.sql.SQLException;
import java.util.HashMap;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = (String) req.getSession().getAttribute(Constants.USER_CODE);
        String userName = req.getParameter("userName");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String userPassword=(String) req.getSession().getAttribute(Constants.USER_PASSWORD);

        UserService userServicelmpl = new UserServicelmpl();

        try {

            int rs = userServicelmpl.update(userCode,userName,birthday,phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        user user = userServicelmpl.login(userCode,userPassword);
        req.getSession().setAttribute(Constants.USER_NAME,user.getUserName());
        req.getSession().setAttribute(Constants.USER_BIRTHDAY,user.getBirthday());
        req.getSession().setAttribute(Constants.USER_PHONE,user.getPhone());

        resp.sendRedirect("information.jsp");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
