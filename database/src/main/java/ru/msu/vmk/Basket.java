package ru.msu.vmk;

import java.sql.SQLException;
import java.util.Map;

public interface Basket {
    /* добавить продукт в корзину с заданным количеством */
    void addProduct(String product, int quantity);

    /* удалить продукт из корзины */
    void removeProduct(String product);

    /* изменить количество продукта */
    void updateProductQuantity(String product, int quantity);

    /* получить список продуктов */
    Map<String, Integer> getProducts();

    void init() throws SQLException;
}