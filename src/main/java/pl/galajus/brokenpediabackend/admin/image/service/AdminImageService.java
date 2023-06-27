package pl.galajus.brokenpediabackend.admin.image.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.common.utils.ExistingFileRenameUtils;
import pl.galajus.brokenpediabackend.admin.common.utils.SlugifyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminImageService {

    @Value("${app.postImagesDir}")
    private String uploadDir;

    public String uploadImage(String fileName, InputStream inputStream) {
        String newFileName = SlugifyUtils.slugifyFileName(fileName);
        newFileName = ExistingFileRenameUtils.renameIfExist(Path.of(uploadDir), newFileName);

        Path filePath = Paths.get(uploadDir).resolve(newFileName);

        try(OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not save a file", e);
        }
        return newFileName;
    }

    public Resource serveFiles(String fileName) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + fileName);
    }
}
