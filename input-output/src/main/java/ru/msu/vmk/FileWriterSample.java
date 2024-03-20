package ru.msu.vmk;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileWriterSample {
    public static void main(String[] args) throws IOException {
        try(FileWriter writer = new FileWriter("./out.txt")) {
            Files.readAllLines(Paths.get("/projects/maven-sample-project/adams.txt")).stream()
                    .filter(line -> !line.isBlank())
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .forEach(word -> {
                        try {
                            writer.write(word);
                            writer.write('\n');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
