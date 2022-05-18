package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Product;
import util.DbUtil;

public class ProductDaoTest {

    private Connection connection;
    private ProductDao productDao;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DbUtil.getConnection();
        connection.setAutoCommit(false);

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM products")) {
            stmt.executeUpdate();
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.addBatch("INSERT INTO products VALUES (101,'鉛筆', 50)");
            stmt.addBatch("INSERT INTO products VALUES (102,'消しゴム', 100)");
            stmt.addBatch("INSERT INTO products VALUES (103,'地球儀', 5000)");
            stmt.addBatch("INSERT INTO products VALUES (104,'ボールペン', 200)");
            stmt.executeBatch();
        }

        productDao = new ProductDao(connection);
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.rollback();
    }

    @Test
    public void findAllで全件取得できる() {
        List<Product> list = productDao.findAll();
        assertEquals(4, list.size());

        Product u = list.get(0);
        assertEquals(Integer.valueOf(101), u.getProductId());
        assertEquals("鉛筆", u.getProductName());
        assertEquals(Integer.valueOf(50), u.getPrice());

        u = list.get(1);
        assertEquals(Integer.valueOf(102), u.getProductId());
        assertEquals("消しゴム", u.getProductName());
        assertEquals(Integer.valueOf(100), u.getPrice());
    }
    
    @Test
    public void findAllはデータがないと空のリストを返す() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM products")) {
            stmt.executeUpdate();
        }

        List<Product> list = productDao.findAll();
        assertEquals(0, list.size());
    }

    @Test
    public void findByProductIdで存在するデータが正しく取得できる() {
        Product product = productDao.findByProductId(101);
        assertEquals(Integer.valueOf(101), product.getProductId());
        assertEquals("鉛筆", product.getProductName());
        assertEquals(Integer.valueOf(50), product.getPrice());
    }

    @Test
    public void findByIdで存在しないデータはnullになる() {
        Product product = productDao.findByProductId(10);
        assertNull(product);
    }

    
}
