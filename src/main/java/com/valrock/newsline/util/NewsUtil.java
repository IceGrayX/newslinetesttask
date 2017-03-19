package com.valrock.newsline.util;

import com.valrock.newsline.model.News;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsUtil {
    private static final org.slf4j.Logger LOG = getLogger(NewsUtil.class);
    private static Random random = new Random();

    public static List<News> getFiltered(Collection<News> newsList, LocalTime startTime, LocalTime endTime) {
        return newsList.stream()
                .filter(news -> DateTimeUtil.isBetween(news.getTime(), startTime, endTime))
                .collect(Collectors.toList());

    }

    public static String saveFile(String path, FileItem item){
        File file;
        String newImageName = "upload/" + random.nextInt() + item.getName();
        do {
            path = path + newImageName;
            file = new File(path);
        } while (file.exists());
        try {
            file.createNewFile();
            item.write(file);
        } catch (Exception e) {
            LOG.info("Exception saveFile");
        }
        return newImageName;
    }

    public static void deleteFile(String path){
        try {
            exists(path);
        } catch (FileNotFoundException e) {
            LOG.info("File to delete not found");
        }
        new File(path).delete();
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
