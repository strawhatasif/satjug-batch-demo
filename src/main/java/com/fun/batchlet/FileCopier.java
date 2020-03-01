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

    public static void copyFile() throws IOException {
        Path source = Paths.get("C:/files/inputFile.txt");
        Path target = Paths.get("C:/java-batch-demo/input/inputFile.txt");

        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
}
