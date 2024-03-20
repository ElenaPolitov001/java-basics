package ru.msu.vmk;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ObjectIoExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/projects/maven-sample-project/book"))) {
//            Book book = new Book(1, "Автостопом по галактике", "Дуглас Адамс");
//            out.writeObject(book);
//        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("/projects/maven-sample-project/book"))) {
            Book book = (Book) in.readObject();
            System.out.println(book);
        }
    }

    public static class Book implements Serializable {
        private static final long serialVersionUID = 5272719709256513983L;
        int id;
        String name;
        String author;

        public Book(int id, String name, String author) {
            this.id = id;
            this.name = name;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    '}';
        }
    }
}
