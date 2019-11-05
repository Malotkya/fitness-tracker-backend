package com.alexmalotky.controller;


import com.alexmalotky.entity.Log;
import com.alexmalotky.entity.User;
import com.alexmalotky.persistance.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet( urlPatterns = {"/Login"} )
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao<Log> dao = new GenericDao<>(Log.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);



    }
}
