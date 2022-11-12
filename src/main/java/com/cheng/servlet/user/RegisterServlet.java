package com.cheng.servlet.user;

import com.cheng.pojo.user;
import com.cheng.service.UserServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//注册
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取值
        String userCode =req.getParameter("userCode");
        String userPassward =req.getParameter("userPassword");
        String userEmail =req.getParameter("email");
        //获取用户
        UserServicelmpl userServicelmpl = new UserServicelmpl();
        user user;
        user = userServicelmpl.login(userCode,userPassward);
        //重复失败
        if (user!=null){
            req.setAttribute("tips","此账户已被注册");
            req.getRequestDispatcher("register.jsp").forward(req,resp);

        }
        else {
            //若未获取到用户，则调用server层方法开始注册
            int rs = 0;
            rs = userServicelmpl.register(userCode,userPassward,userEmail);
            resp.sendRedirect("personal page.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
