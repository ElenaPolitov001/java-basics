package ru.msu.vmk;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/*
        Postgresql
            jdbc:postgresql://host:port/database
        MySQL
            jdbc:mysql://host:port/database
        Derby
            jdbc:derby:D:/dev/projects/database;create=true
            jdbc:derby:memory:database;create=true
        H2
            jdbc:h2:file:C:/data/sample
            jdbc:h2:mem:Database
*/

public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        Basket basket = new BasketImpl("jdbc:h2:mem:basket", "", "");
        //Basket basket = new BasketImpl("jdbc:derby:memory:basket;create=true", "", "");

        //init db
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:basket");
        //Connection connection = DriverManager.getConnection("jdbc:derby:memory:basket;create=true");
        try(Statement stmt = connection.createStatement();) {
            String tableSql = "create table basket(product varchar(100), quantity int)";
            stmt.execute(tableSql);
        }



        basket.addProduct("Product 1", 1);
        basket.addProduct("Product 2", 2);
        basket.addProduct("Product 3", 3);
        basket.addProduct("Product 4", 4);
        basket.updateProductQuantity("Product 1", 4);
        basket.removeProduct("Product 4");

        Map<String, Integer> products = basket.getProducts();
        log.info(products);

    }
}