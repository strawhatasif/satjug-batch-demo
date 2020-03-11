package com.fun.batchlet;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopier extends AbstractBatchlet {

    public FileCopier() {
    }

    @Override
    public String process() throws Exception {
        copyFile();
        return BatchStatus.COMPLETED.toString();
    }

    /**
     * An elementary example showing file copy.
     * For the sake of making this run locally, I've placed the file within another directory in this project
     *
     * In a real world scenario, the location could be another server.
     * @throws IOException
     */
    public static void copyFile() throws IOException {
        Path source = Paths.get("C:/satjug-batch-demo/sourceDir/inputFile.txt");
        Path target = Paths.get("C:/satjug-batch-demo/input/inputFile.txt");

        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
}
