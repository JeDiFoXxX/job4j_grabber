package ru.job4j.grabber.stores;

import org.apache.logging.log4j.*;
import ru.job4j.grabber.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStore implements Store {
    private static final Logger LOG = LogManager.getLogger(JdbcStore.class);
    private final Connection connection;

    public JdbcStore(Connection connection) {
        this.connection = connection;
    }

    private Post createPost(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getLong(1));
        post.setTitle(resultSet.getString(2));
        post.setLink(resultSet.getString(3));
        post.setDescription(resultSet.getString(4));
        post.setTime(resultSet.getTimestamp(5) != null
                ? resultSet.getTimestamp(5).getTime() : 0);
        return post;
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO post (title, link, description, time) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getLink());
            statement.setString(3, post.getDescription());
            Timestamp timestamp = post.getTime() != null ? new Timestamp(post.getTime()) : null;
            statement.setTimestamp(4, timestamp);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error saving post : {}", post, e);
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(createPost(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Error fetching all posts", e);
        }
        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Optional<Post> optional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optional = Optional.of(createPost(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Error finding post by id : {}", id, e);
        }
        return optional;
    }
}