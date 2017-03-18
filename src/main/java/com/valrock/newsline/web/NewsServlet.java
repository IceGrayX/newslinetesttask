package com.valrock.newsline.web;

import com.valrock.newsline.model.News;
import com.valrock.newsline.web.news.NewsRestController;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsServlet extends HttpServlet {
    private static final Logger LOG = getLogger(NewsServlet.class);

    private ConfigurableApplicationContext springContext;
    private NewsRestController newsController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        newsController = springContext.getBean(NewsRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        final News news = new News(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("header"),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("textnews"),
                request.getParameter("imageURL"));

        if (news.isNew()){
            LOG.info("Create {}", news);
        } else {
            LOG.info("Update {}", news);
            newsController.update(news, getId(request));
        }
        response.sendRedirect("newsline");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null){
            LOG.info("getAll");
            request.setAttribute("newsline", newsController.getAll());
            request.getRequestDispatcher("/newsline.jsp").forward(request, response);
        } else if ("delete".equals(action)){
            int id = getId(request);
            LOG.info("Delete {}", id);
            newsController.delete(id);
            response.sendRedirect("newsline");
        } else if ("create".equals(action) || "update".equals(action)){
            final News news = action.equals("create") ?
                    new News("", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", "") :
                    newsController.get(getId(request));
            request.setAttribute("news", news);
            request.getRequestDispatcher("news.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
