package pl.galajus.brokenpediabackend.admin.common.utils;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExistingFileRenameUtils {

    /**
     * test.png
     * test-1.png
     * test-2.png
     * test-3.png
     */
    public static String renameIfExist(Path uploadDir, String fileName) {
        if (Files.exists(uploadDir.resolve(fileName))) {
            return renameAndCheck(uploadDir, fileName);
        }
        return fileName;
    }

    private static String renameAndCheck(Path uploadDir, String fileName) {
        String newName = renameFileName(fileName);
        if (Files.exists(uploadDir.resolve(newName))) {
            newName = renameAndCheck(uploadDir, newName);
        }
        return newName;
    }

    private static String renameFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        //test-1
        String[] split = name.split("-(?=[0-9]+$)");
        int counter = split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;

        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }
}
