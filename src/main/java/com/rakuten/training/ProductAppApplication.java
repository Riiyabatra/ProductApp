package com.rakuten.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Review;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		//ApplicationContext springContainer = 
				
		SpringApplication.run(ProductAppApplication.class, args);
		//ProductConsoleUI ui =  springContainer.getBean(ProductConsoleUI.class);
		//ui.createProductWithUI();
		/*ReviewDAO reviewDAO = springContainer.getBean(ReviewDAO.class);
		Review sample = new Review("self", "this is good", 5);
		Review saved = reviewDAO.save(sample,1);
		System.out.println("Created review with id:"+saved.getId());*/
		/*ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
		Product p =productDAO.findById(1);
		System.out.println(p.getName());
		//getting object here in detached state. works only after adding eager property in one to many.
		System.out.println("This product has " +p.getReviews().size()+"reviews");*/
		/*ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
		List<Product> p = productDAO.findAll();
		for(int i=0; i<p.size(); i++) {
            System.out.println(p.get(i).getName());
        }*/
		/*ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
		productDAO.deleteById(1);*/
		
		
	}

}
