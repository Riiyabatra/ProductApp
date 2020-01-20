package com.rakuten.training.web;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;
//which frameworks spring should keep ready. 
@RunWith(SpringRunner.class)
@WebMvcTest({ProductController.class}) // all the dependencies of review service should be mocked. in my case review and product both are in product controller
public class ProductControllerUnitTest {

	@Autowired //to make spring inject object automatically.
	MockMvc mockMvc;
	
	@MockBean
	ProductService service;
	
	@MockBean
	ReviewService rev;
	@Test
	public void getProductById_Returns_OK_With_Correct_Product_If_Found() throws Exception {
		//Arrange
		Product found = new Product("test",123.0f,100);
		int id = 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(found);
		
		//Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
	}

	@Test
	public void addProduct_Returns_No_Exception() throws Exception {
		//Arrange
		//Product added = new Product("test", 11.9f, 88);
// 1 OBJECT		
//		int id = 1;				
//		added.setId(id);
//	    Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
//		
		//Act and Assert
		//mockMvc.perform(MockMvcRequestBuilders.get("/products"))
		//	.andExpect(MockMvcResultMatchers.status().isCreated())
		//	.and;
		
//		 String jsonString="{\"name\": \"ghj\", \"price\": \"80\", \"qoh\": \"10000\" } ";
//		    mockMvc.perform(MockMvcRequestBuilders
// ANOTHER OBJECT		    		
//		    		.post(URI.create("/products"))
//		    		.contentType(MediaType.APPLICATION_JSON)
//		    		.content(jsonString))
//		    		.andExpect(MockMvcResultMatchers.status().isCreated());
//		
//		
//		 Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class)); // it will be called with secong object
		 // Arrange
	    Product added = new Product("test", 123.0f, 100);
	    int id = 1;
	    added.setId(id);

	    ObjectMapper mapper = new ObjectMapper();

	    // Act & Assert
	    Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
	    
	    mockMvc
	        .perform(
	            MockMvcRequestBuilders.post("/products")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(added)))
	        .andExpect(MockMvcResultMatchers.status().isCreated())
	        .andExpect(MockMvcResultMatchers.header().string("location", "/products/"+ id));

	    Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void addProduct_Throws_Exception() throws Exception {
//		
//		Product added = new Product("test", 11.9f, 8);
//		// 1 OBJECT		
//		int id = 1;				
//		added.setId(id);
//		ObjectMapper mapper = new ObjectMapper();
//		//Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenThrow(IllegalArgumentException.class);
//		Mockito.doThrow(IllegalArgumentException.class).when(service).addNewProduct(added);
//		//Mockito.doNothing().when(service).removeProduct(id);
//
//		mockMvc
//        .perform(
//            MockMvcRequestBuilders.post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(added)))
//        .andExpect(MockMvcResultMatchers.status().isBadRequest())
//        .andExpect(MockMvcResultMatchers.header().string("location", "/products/"+ id));
//		
//		
//		Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
//	}
	
	
}
