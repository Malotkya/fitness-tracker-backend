package com.alexmalotky.controller;



import com.alexmalotky.entity.User;
import com.alexmalotky.persistance.GenericDao;
import com.alexmalotky.util.JsonParser;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet( urlPatterns = {"/Login"} )
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao<User> dao = new GenericDao<>(User.class);
        response.setContentType("application/json");
        PrintWriter dom = response.getWriter();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        List<User> list = dao.findByPropertyEqual("userName", userName);

        if(list.size() == 1) {
            User user = list.get(0);
            if(user.getPassword().equals(password)) {
                dom.print(JsonParser.stringify(user));
                //TODO: Add key to session for security
            } else {
                dom.print(JsonParser.errorCode("UserName or Password Incorrect!", 401));
            }
        } else {
            dom.print(JsonParser.errorCode("Database Error!", 500));
        }

    }
}
