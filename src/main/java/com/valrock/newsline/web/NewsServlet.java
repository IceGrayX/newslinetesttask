package com.valrock.newsline.web;

import com.valrock.newsline.model.News;
import com.valrock.newsline.util.NewsUtil;
import com.valrock.newsline.web.news.NewsRestController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsServlet extends HttpServlet {
    private static final Logger LOG = getLogger(NewsServlet.class);

    private ConfigurableApplicationContext springContext;
    private NewsRestController newsController;
    private DiskFileItemFactory diskFileItemFactory;
    private ServletFileUpload upload;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        newsController = springContext.getBean(NewsRestController.class);
        diskFileItemFactory = new DiskFileItemFactory(1024 * 1024 * 10, new File("/temp/"));
        upload = new ServletFileUpload(diskFileItemFactory);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart){
            try {
                String id = null;
                String header = null;
                String dateTime = null;
                String textnews = null;
                String imageName = null;
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()){
                    FileItem item = (FileItem) iterator.next();
                    if (item.isFormField()){
                        switch (item.getFieldName()){
                            case "id": id = item.getString("UTF-8");
                                        break;
                            case "header": header = item.getString("UTF-8");
                                break;
                            case "dateTime": dateTime = item.getString("UTF-8");
                                break;
                            case "textnews": textnews = item.getString("UTF-8");
                                break;
                        }
                    } else {
                        String path = getServletContext().getRealPath("");
                        imageName = NewsUtil.saveFile(item.getName(), path, item);
                    }
                }
                News news = new News(id.isEmpty() ? null : Integer.valueOf(id),
                        header, LocalDateTime.parse(dateTime), textnews, imageName);

                if (news.isNew()){
                    LOG.info("Create {}", news);
                    newsController.create(news);
                } else {
                    LOG.info("Update {}", news);
                    newsController.update(news, getId(request));
                }
                response.sendRedirect("newsline");
            } catch (FileUploadException e) {
                LOG.info("Exception upload file");
            }
        }
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
            String path = getServletContext().getRealPath("");
            newsController.delete(id, path);
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
