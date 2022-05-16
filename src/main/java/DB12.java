
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB12 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        int searchId = 101;
        String searchName = "地球儀";

        try {
            // load JDBC Driver
            Class.forName("org.postgresql.Driver");

            // confirm
            System.out.println(" --- before connection --- ");

            // database connect
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "axizuser", "axiz");

            // confirm
            System.out.println(" --- after connection --- ");

            // SQL query string
            String sql = "SELECT * FROM products WHERE product_id = " + searchId + " OR product_name = '" + searchName + "' ORDER BY product_id ASC";

            // create statement
            stmt = con.prepareStatement(sql);

            // execute
            ResultSet rs = stmt.executeQuery();

            // output
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String price = rs.getString("price");
                String data = "";
                data += "product_id:" + id + ",";
                data += "product_name:" + name + ",";
                data += "price:" + price;
                
                System.out.println(data);
       
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
