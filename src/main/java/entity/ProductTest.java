package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void usersテーブルに対応している() {
        Product user = new Product();
        user.setProductId(1);
        user.setProductName("test");
        user.setPrice(100);

        assertEquals(Integer.valueOf(1), user.getProductId());
        assertEquals("test", user.getProductName());
        assertEquals(Integer.valueOf(100), user.getPrice());
    }

    @Test
    public void 引数のあるコンストラクターがある() {
        Product user = new Product(1, "test", 100);

        assertEquals(Integer.valueOf(1), user.getProductId());
        assertEquals("test", user.getProductName());
        assertEquals(Integer.valueOf(100), user.getPrice());
    }

}
