package ru.msu.vmk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class DecoratorSample {

    public static void main(String[] args) throws Exception {

        /*InputStream inputStream =
                new BufferedInputStream(
                        new ZipInputStream(
                                new FileInputStream("/projects/maven-sample-project/adams.txt")
                        )
                );*/

        var stream = new ByteArrayInputStream(new byte[] {1,2,3,4,5});
        //var fileStream = new FileInputStream("/projects/maven-sample-project/adams.txt");
        URL url = new URL("https://unix.stackexchange.com/questions/36841/why-is-number-of-open-files-limited-in-linux");

        //ReadStream(stream);

        /*try (var fileOutputStream = new FileOutputStream("D:\\university\\postgraduate\\practice\\java-basics\\javafirst-maven\\index.html"))
        {
            url.openStream().transferTo(fileOutputStream);
        }
        new StringReader("Длинная строка");

        try (Reader reader = new InputStreamReader(url.openStream())) {
            int c;
            while((c = reader.read()) != -1) {
                System.out.print((char)c);
            }
        }*/

        var user = Files.readAllLines(Path.of(ClassLoader.getSystemResource("user.csv").toURI()))
                .stream()
                .skip(1)
                .map(User::Parse)
                .max(Comparator.comparingInt(x -> x.age))
                .get();

        System.out.println(user);
    }

    private static void ReadStream(InputStream stream) throws IOException {
        while (true){
            var element = stream.read();

            if(element == -1){
                break;
            }

            System.out.print(element);
        }
    }

    private static class CloseableClass implements AutoCloseable{

        @Override
        public void close() throws Exception {
            System.out.println("Closeable works");
        }
    }

    private static class User{
        private int id;
        private int age;
        private String name;


        private User(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Id = " + id + " Name = "+name + " Age = " + age;
        }

        public static User Parse(String input){
            var splited = input.split(",");

            var id = Integer.parseInt(splited[0]);
            var name = splited[1];
            var age = Integer.parseInt(splited[2]);

            return new User(id, age, name);
        }
    }
}
