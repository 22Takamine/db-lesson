import java.sql.Connection;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class DbDevExam5_Select {
	public static void main(String[] args) {
    	Connection connection;
	   	ProductDao productDao;
	   	String textId = "product_id:";
	   	String textName = "product_name:";
	   	String textPrice = "price:";
	   	int selectId = 102;
	   	 
	   	connection = DbUtil.getConnection();
	   	productDao = new ProductDao(connection);
	   	Product product = productDao.findByProductId(selectId);
	   	
        
        System.out.print(textId + product.getProductId() + ",");
    	System.out.print(textName +product.getProductName() + ",");
    	System.out.println(textPrice + product.getPrice());
        	
        
    }

}
