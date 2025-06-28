package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        if (!Files.exists(Path.of(cachingDir))) {
            throw new IllegalStateException("Директория для кэша не найдена: " + cachingDir);
        }
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl;
        Path filePath = Path.of(cachingDir, key);
        if (!Files.exists(filePath)) {
            throw new IllegalStateException("Файл " + key + " не найден в директории " + cachingDir);
        }
        try (Stream<String> read = Files.lines(filePath)) {
            rsl = read.collect(Collectors.joining());
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка чтения файла " + key, e);
        }
        if (rsl.isEmpty()) {
            throw new IllegalStateException("Файл " + key + " пустой или отсутствует содержимое");
        }
        return rsl;
    }
}