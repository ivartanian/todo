package com.mbrunarskiy.todo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author maks
 */
public interface IOUtils {

    /**
     * Save file
     *
     * @param file file which needs save
     * @return file path
     */
    String write(MultipartFile file);

    default void preparePath(String path) throws IOException {
        Path directory = Paths.get(path);
        if (!directory.toFile().exists()) {
            Files.createDirectories(directory);
        }
    }
}
