package com.mbrunarskiy.todo.utils;

import com.mbrunarskiy.todo.exceptions.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.config.TikaConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author maks
 */
@Service
@Slf4j
public class IOUtilsImpl implements IOUtils {

    @Value("${app.path.project}")
    private String projectPath;

    @Value("${app.path.files}")
    private String filesPath;

    @Override
    public String write(MultipartFile file) {
        if (isNull(file)) {
            return "";
        }
        byte[] bytes;
        String fileName = UUID.randomUUID().toString();
        String extension = "";
        try {
            bytes = file.getBytes();
            if (nonNull(file.getContentType())){
                extension = TikaConfig.getDefaultConfig().getMimeRepository().forName(file.getContentType()).getExtension();
            }
        } catch (Exception e) {
            throw new PersistenceException("Couldn't read file", e);
        }
        try {
            preparePath(projectPath + filesPath);
            Files.write(Paths.get(projectPath + filesPath + fileName + extension), bytes);
        } catch (IOException e) {
            throw new PersistenceException("Couldn't save file", e);
        }
        return filesPath + fileName + extension;
    }

}
