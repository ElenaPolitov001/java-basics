package ru.msu.vmk;

import java.util.List;

/**
 * Реализация через файлы и базу данных.
 */
public class LibraryImpl implements Library {

    private String jdbcUrl;
    private String user;
    private String password;

    public LibraryImpl(String jdbcUrl, String user, String password) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }

    @Override
    public void addNewBook(Book book) {

    }

    @Override
    public void borrowBook(Book book, String student) {

    }

    @Override
    public void returnBook(Book book, String student) {

    }

    @Override
    public List<Book> findAvailableBooks() {
        return null;
    }
}
