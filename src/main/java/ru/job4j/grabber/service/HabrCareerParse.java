package ru.job4j.grabber.service;

import org.apache.logging.log4j.*;
import org.jsoup.Jsoup;

import ru.job4j.grabber.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final Logger LOG = LogManager.getLogger(HabrCareerParse.class);
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PREFIX = "/vacancies?page=";
    private static final String SUFFIX = "&q=Java%20developer&type=all";

    @Override
    public List<Post> fetch() {
        var result = new ArrayList<Post>();
        try {
            int pageNumber = 1;
            String fullLink = "%s%s%d%s".formatted(SOURCE_LINK, PREFIX, pageNumber, SUFFIX);
            var connection = Jsoup.connect(fullLink);
            var document = connection.get();
            var rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                var post = new Post();
                var titleElement = row.select(".vacancy-card__title").first();
                var dateElement = row.select(".vacancy-card__date").first();
                if (titleElement != null) {
                    String vacancyName = titleElement.text();
                    post.setTitle(vacancyName);
                    var linkElement = titleElement.firstChild();
                    if (linkElement != null) {
                        String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                        post.setLink(link);
                    }
                }
                if (dateElement != null) {
                    var timeElement = dateElement.firstChild();
                    if (timeElement != null) {
                        /*Тут должна быть дата post.setTime(time);*/
                        String smile = "=)";
                    }
                }
                result.add(post);

            });
        } catch (IOException e) {
            LOG.error("When load page", e);
        }
        return result;
    }
}