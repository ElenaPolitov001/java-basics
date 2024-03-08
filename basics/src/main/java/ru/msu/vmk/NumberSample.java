package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {

    public static void main(String[] args) {
        // вызов split - проверка работы
    }

    public static BigDecimal[] split(BigDecimal amount, int n) {
        BigDecimal[] result = new BigDecimal[n];
        BigDecimal splitAmount = amount.divide(new BigDecimal(n), 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal remainder = amount.remainder(new BigDecimal(n));

        for (int i = 0; i < n; i++) {
            result[i] = splitAmount;
        }
        
        result[0] = result[0].add(remainder);

        return result;
    }
}
