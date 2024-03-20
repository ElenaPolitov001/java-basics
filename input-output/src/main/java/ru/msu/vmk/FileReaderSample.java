package ru.msu.vmk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileReaderSample {
    public static void main(String[] args) throws IOException {
//        try (FileReader reader = new FileReader("/projects/maven-sample-project/adams_ansi.txt", Charset.forName("windows-1251"))) {
//            int c = 0;
//            while((c = reader.read()) != -1) {
//                System.out.println((char)c);
//            }
//        }

        List<String> lines = Files.readAllLines(Paths.get("/projects/maven-sample-project/adams.txt"));
        for(String line : lines) {
            System.out.println(line);
        }

        Files.readAllLines(Paths.get("/projects/maven-sample-project/adams.txt")).stream()
                .filter(line -> !line.isBlank())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .forEach(word -> System.out.println(word));
    }
}
