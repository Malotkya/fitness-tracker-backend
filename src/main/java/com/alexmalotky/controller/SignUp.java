package com.alexmalotky.controller;

import com.alexmalotky.entity.User;
import com.alexmalotky.persistance.JsonDao;
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


@WebServlet( urlPatterns = {"/SignUp"} )
public class SignUp extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonDao dao = new JsonDao();
        response.setContentType("application/json");
        PrintWriter dom = response.getWriter();

        String userName = request.getParameter("userName");
        String password = BCrypt.hashpw(request.getParameter("password") , BCrypt.gensalt());
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        try {
            User newUser = new User(userName, password, firstName, lastName, email);
            String user = dao.insert(newUser);
            logger.debug(user);
            dom.write(user);

        } catch(Exception e) {
            response.setStatus(500);
            dom.write(e.getMessage());
            logger.error(e.getStackTrace());
        }

    }
}
