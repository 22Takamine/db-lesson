import java.sql.Connection;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class DbDevExam5_Update {
	public static void main(String[] args) {
    	Connection connection;
	   	ProductDao productDao;
	   	
	   	String textId = "product_id:";
	   	String textName = "product_name:";
	   	String textPrice = "price:";
	   	int selectId = 60;
	   	 
	   	connection = DbUtil.getConnection();
	   	productDao = new ProductDao(connection);
	   	Product product = productDao.findByProductId(selectId);
	   	
	   	System.out.println("【更新前】");
        System.out.print(textId + product.getProductId() + ",");
    	System.out.print(textName +product.getProductName() + ",");
    	System.out.println(textPrice + product.getPrice());
        
    	
    	product.setProductName("シャープペンシル");
    	product.setPrice(70);
    	
        productDao.update(product);

        product = productDao.findByProductId(60);
        System.out.println("【更新後】");
        System.out.print(textId + product.getProductId() + ",");
    	System.out.print(textName +product.getProductName() + ",");
    	System.out.println(textPrice + product.getPrice());
        
    }

}
