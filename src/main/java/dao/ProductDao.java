package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {

    private static final String SQL_SELECT_ALL = "SELECT product_id, product_name, price FROM products ORDER BY product_id";
    private static final String SQL_SELECT_WHERE_USER_ID = "SELECT product_id, product_name, price FROM products WHERE product_id = ?";
    private static final String SQL_REGISTER = "INSERT INTO products (product_name, price) VALUES (?, ?)";
    private static final String SQL_INSERT = "INSERT INTO products (product_id, product_name, price) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE products SET product_name = ?, price = ? WHERE product_id = ?";
    private static final String SQL_DELETE = "DELETE FROM products WHERE product_name = ?";

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("product_name"),rs.getInt("price"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    
    public void register(Product product) {
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_REGISTER)) {
            //stmt.setInt(1, product.getProductId());
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getPrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Product findByProductId(int productId) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_USER_ID)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public int insert(Product product) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setInt(3, product.getPrice());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int update(Product product) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getPrice());
            stmt.setInt(3, product.getProductId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String productName) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setString(1, productName);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
