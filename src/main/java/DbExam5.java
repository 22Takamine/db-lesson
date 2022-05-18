import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class DbExam5 {
    public static void main(String[] args) {
    	Connection connection;
	   	ProductDao productDao;
	   	Product data;
	   	String textId = "product_id:";
	   	String textName = "product_name:";
	   	String textPrice = "price:";
	  	String name = "ボールペン";
		int price = 200;
	   	 
	   	connection = DbUtil.getConnection();
	   	productDao = new ProductDao(connection);
	   	
	   	Product newProduct = new Product(name,price);
        productDao.register(newProduct);
	   	
    	List<Product> list = productDao.findAll();
        
        for(int i = 0; i < list.size(); i++) {
        	data = list.get(i);
        	
        	System.out.print(textId + data.getProductId() + ",");
        	System.out.print(textName +data.getProductName() + ",");
        	System.out.println(textPrice + data.getPrice());
        	
        }
    }
    
}