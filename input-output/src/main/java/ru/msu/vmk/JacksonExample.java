package ru.msu.vmk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;

public class JacksonExample {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
/*        try (OutputStream out = new FileOutputStream("./book.json")) {
            Book book = new Book(10, "Автостопом по галактике", "Дуглас Адамс");
            mapper.writeValue(out, book);
        }*/

        try (InputStream in = new FileInputStream("./book.json")) {
            Book book = mapper.readValue(in, Book.class);
            System.out.println(book);
        }
    }

    public static class Book implements Serializable {
        private static final long serialVersionUID = 5272719709256513983L;
        int id;
        int isbn;
        String name;
        String author;

        public Book() {
        }

        public Book(int id, String name, String author) {
            this.id = id;
            this.name = name;
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", isbn=" + isbn +
                    '}';
        }
    }
}
