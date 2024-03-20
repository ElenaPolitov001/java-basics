package ru.msu.vmk;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BasketImpl implements Basket {

    private String jdbcUrl;
    private String user;
    private String password;

    public BasketImpl(String jdbcUrl, String user, String password) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    public void addProduct(String product, int quantity) {
        String addSql = "insert into basket values('"+product+"', "+quantity+")";
        try (Connection c = getConnection()) {     // initialize a connection to db

            c.setAutoCommit(false); //

            try(Statement stmt = getConnection().createStatement();) {
                stmt.execute(addSql);
                c.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                c.rollback();
            }

            c.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(String product) {
        String removeSql = "delete from basket where product = ?";
        try(PreparedStatement stmp = getConnection().prepareStatement(removeSql)){
            stmp.setString(1, product);
            stmp.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductQuantity(String product, int quantity) {
        String updateSql = "update basket set quantity = ? where product = ?";
        try (CallableStatement stmt = getConnection().prepareCall(updateSql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, product);
            stmt.executeUpdate();
            //getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Integer> getProducts() {
        Map<String, Integer> result = new HashMap<>();
        String sql = "select product, quantity from basket";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Integer quantity = rs.getInt("quantity");
                String product = rs.getString("product");
                result.put(product, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void init() throws SQLException {
        try (Statement stmt = getConnection().createStatement()){
            stmt.execute("create table basket(product varchar(20), quantity int)");
        }
    }
}