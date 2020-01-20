package com.rakuten.training.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest1 {

	@Test
	public void addNewProduct_Returns_Valid_Id_When_ProductValue_GTEQ_MinValue() {
		
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeAdded = new Product("test",10000,1); //notice 10000*1 >= 10000
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class); // this tells Mockito to give a mock object. But can't use right away it has to be trained first.
		Product saved = new Product("test", 10000, 1);
		saved.setId(1);
		Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
		service.setDao(mockDAO);
		
		//Act
		int id = service.addNewProduct(toBeAdded);
		
		//Assert
		assertTrue(id>0);
		
		// fail("Not yet implemented");
		// Classes which have a dependency on separate class, a different method is used. This depends on DAO object. 
		// There is no spring here so no dependency injection is happening.
		// Let's give it a DAO manually.
		// I want an object which implements DAO interface and has just one save method to test this.
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNewProduct_Throws_When_ProductValue_LT_MinValue() {
		
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeAdded = new Product("test",9999,1); //notice 9999*1 >= 10000
		
		//Act
		int id = service.addNewProduct(toBeAdded);
		
		//Assert
		
	}
	
	@Test
	public void removeProduct_Deletes_Product_When_ProductValue_LT_MinValue() {
		
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeDeleted = new Product("test",9999,1); //notice 10000*1 >= 10000
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class); // this tells Mockito to give a mock object. But can't use right away it has to be trained first.
		Product saved = new Product("test", 9999, 1);
		saved.setId(1);
		Mockito.when(mockDAO.findById(1)).thenReturn(toBeDeleted);
		service.setDao(mockDAO);
		
		//Act
		service.removeProduct(1);
		
		//Assert
		Mockito.verify(mockDAO).deleteById(1);
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeProduct_Throws_When_ProductValue_GTEQ_MinValue() {
		
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeDeleted = new Product("test",100000,1);
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class); // this tells Mockito to give a mock object. But can't use right away it has to be trained first.
		Product saved = new Product("test", 100000, 1);
		saved.setId(1);
		Mockito.when(mockDAO.findById(1)).thenReturn(toBeDeleted);
		service.setDao(mockDAO);
		
		//Act
		service.removeProduct(saved.getId());
		
		//Assert
		Mockito.verify(mockDAO);
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeProduct_Return_Exception_If_Null() {
		ProductServiceImpl service = new ProductServiceImpl();
		Product saved = null;
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class); // this tells Mockito to give a mock object. But can't use right away it has to be trained first
		Mockito.when(mockDAO.findById(1)).thenReturn(saved);
		service.setDao(mockDAO);
		service.removeProduct(1);
		
	}

}
