package com.alexmalotky.controller;



import com.alexmalotky.entity.User;
import com.alexmalotky.persistance.GenericDao;
import com.alexmalotky.util.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

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

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao<User> dao = new GenericDao<>(User.class);
        response.setContentType("application/json");
        PrintWriter dom = response.getWriter();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> list = dao.findByPropertyEqual("userName", userName);

        if(list.size() == 1) {
            User user = list.get(0);

            if(BCrypt.checkpw(password,user.getPassword())) {
                dom.print(JsonParser.stringify(user));
                //TODO: Add key to session for security

            } else { // Invalid password
                dom.print("UserName or Password Incorrect!");
                response.setStatus(401);
            }
        } else { //Invalid username
            dom.print("UserName or Password Incorrect!");
            response.setStatus(401);
        }

    }
}
