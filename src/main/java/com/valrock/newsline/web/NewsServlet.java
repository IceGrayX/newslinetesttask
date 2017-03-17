package com.valrock.newsline.web;

import com.valrock.newsline.AuthorizedUser;
import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.mock.InMemoryNewsRepositoryImpl;
import com.valrock.newsline.repository.NewsRepository;
import org.slf4j.Logger;

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

    private NewsRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryNewsRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        News news = new News(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("header"),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("textnews"),
                request.getParameter("imageURL"));

        LOG.info(news.isNew() ? "Create {}" : "Update {}", news);
        repository.save(news, AuthorizedUser.id());
        response.sendRedirect("newsline");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null){
            LOG.info("getAll");
            request.setAttribute("newsline", repository.getAll(AuthorizedUser.id()));
            request.getRequestDispatcher("/newsline.jsp").forward(request, response);
        } else if ("delete".equals(action)){
            int id = getId(request);
            LOG.info("Delete {}", id);
            repository.delete(id, AuthorizedUser.id());
            response.sendRedirect("newsline");
        } else if ("create".equals(action) || "update".equals(action)){
            final News news = action.equals("create") ?
                    new News("", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", "") :
                    repository.get(getId(request), AuthorizedUser.id());
            request.setAttribute("news", news);
            request.getRequestDispatcher("news.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
