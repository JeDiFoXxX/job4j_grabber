package ru.job4j;

import ru.job4j.grabber.model.Post;
import ru.job4j.grabber.service.*;
import ru.job4j.grabber.stores.JdbcStore;

import org.apache.logging.log4j.*;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        var config = new Config();
        config.load("./src/main/resources/application.properties");
        try (Connection connection = DriverManager.getConnection(
                config.get("db.url"),
                config.get("db.username"),
                config.get("db.password")
        )) {
            var store = new JdbcStore(connection);
            HabrCareerParse careerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
            for (Post post : careerParse.fetch()) {
                store.save(post);
            }
            var scheduler = new SchedulerManager();
            scheduler.init();
            scheduler.load(
                    Integer.parseInt(config.get("rabbit.interval")),
                    SuperJobGrab.class,
                    store);
            new Web(store).start(Integer.parseInt(config.get("server.port")));
        } catch (SQLException e) {
            LOG.error("When create a connection", e);
        }
    }
}