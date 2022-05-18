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
    private static final String SQL_SELECT_WHERE_USER_ID_AND_PRICE = "SELECT product_id, product_name, price FROM products WHERE product_id = ? OR price = ?";
    private static final String SQL_SELECT_SERCH = "SELECT product_id, product_name, price FROM products WHERE product_id = ? OR price = ? ORDER BY product_id";
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
    
    public Product findByProductIdAndPrice(int productId, int price) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_USER_ID_AND_PRICE)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, price);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public List<Product> findSerch(int productId, int price) {
        List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_SERCH)) {
        	stmt.setInt(1, productId);
            stmt.setInt(2, price);
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


}
