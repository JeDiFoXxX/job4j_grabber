package ru.job4j.grabber.service;

import org.apache.logging.log4j.*;
import org.jsoup.Jsoup;

import ru.job4j.grabber.model.Post;
import ru.job4j.grabber.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final Logger LOG = LogManager.getLogger(HabrCareerParse.class);
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PREFIX = "/vacancies?page=";
    private static final String SUFFIX = "&q=Java%20developer&type=all";
    private static final int MAX_PAGES = 5;
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private static String retrieveDescription(String link) {
        String result = null;
        try {
            var connection = Jsoup.connect(link);
            var document = connection.get();
            var descriptionElement = document.select(".style-ugc").first();
            if (descriptionElement != null) {
                result = descriptionElement.text();
            }
        } catch (IOException e) {
            LOG.error("Failed to load page for link: {}", link, e);
        }
        return result;
    }

    @Override
    public List<Post> fetch() {
        var result = new ArrayList<Post>();
        try {
            for (int pageNumber = 1; pageNumber <= MAX_PAGES; pageNumber++) {
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
                            post.setDescription(retrieveDescription(link));
                            post.setLink(link);
                        }
                    }
                    if (dateElement != null) {
                        var timeElement = dateElement.firstChild();
                        if (timeElement != null) {
                            String careerDate = timeElement.attr("datetime");
                            String localDateTime = dateTimeParser.parse(careerDate)
                                    .toString().replaceAll("[-T:]", "");
                            post.setTime(Long.parseLong(localDateTime));
                        }
                    }
                    result.add(post);
                });
            }
        } catch (IOException e) {
            LOG.error("When load page", e);
        }
        return result;
    }

    @Override
    public List<Post> list(String link) {
        var result = new ArrayList<Post>();
        try {
            for (int pageNumber = 1; pageNumber <= MAX_PAGES; pageNumber++) {
                String fullLink = "%s%s%d%s".formatted(link, PREFIX, pageNumber, SUFFIX);
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
                            String linkVacancy = String.format("%s%s", link, linkElement.attr("href"));
                            post.setDescription(retrieveDescription(linkVacancy));
                            post.setLink(linkVacancy);
                        }
                    }
                    if (dateElement != null) {
                        var timeElement = dateElement.firstChild();
                        if (timeElement != null) {
                            String careerDate = timeElement.attr("datetime");
                            String localDateTime = dateTimeParser.parse(careerDate)
                                    .toString().replaceAll("[-T:]", "");
                            post.setTime(Long.parseLong(localDateTime));
                        }
                    }
                    result.add(post);
                });
            }
        } catch (IOException e) {
            LOG.error("When load page", e);
        }
        return result;
    }
}