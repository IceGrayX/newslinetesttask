package com.valrock.newsline.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsServlet extends HttpServlet {
    private static final Logger LOG = getLogger(NewsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to news");
//        request.getRequestDispatcher("/news.jsp").forward(request, response);
        response.sendRedirect("news.jsp");
    }
}
