package com.alexmalotky.controller;


import com.alexmalotky.persistance.JsonDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet( urlPatterns = {"/Update"} )
public class Update extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter dom = response.getWriter();
        JsonDao dao = new JsonDao();
        response.setContentType("application/json");

        try
        {
            String user = request.getParameter("user");
            user = dao.saveOrUpdate(user);
            dom.write(user);

        } catch (Exception e) {
            response.setStatus(500);
            dom.write(e.getMessage());
            logger.error(e.getStackTrace());
        }


    }
}
