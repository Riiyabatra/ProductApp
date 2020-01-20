package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.NoSuchProductException;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
// RestController tells that whatever objects are returned are converted to json and then sent to the client.(placed in response body)
public class ProductController {


	@Autowired //no need of setter getter. (something else also)
	ProductService service;
	
	@Autowired
	ReviewService rev;
	//@RequestMapping(method = RequestMethod.GET, value = "/products") //mapping this method to request. whenever this request comes call this method.
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return service.findAll(); //it appears that it is returning java list of products but spring boot converts and sends in json format.
	}
	
	@GetMapping("/products/{id}") //we are not really sending the id but just a place holder for an id. just a path variable.
	// to tell spring from where to take id value
	public ResponseEntity<Product> getProductById(@PathVariable("id")int id) { 
		Product p = service.findById(id);
		if(p!=null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product toBeAdded){ //this product comes from body of the object
		try {
			int id = service.addNewProduct(toBeAdded);
			System.out.println(toBeAdded.getPrice());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e){
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id")int id) {
		try {
			service.removeProduct(id);
			return new ResponseEntity<Product>(HttpStatus.OK);
		}
		catch(IllegalStateException e) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/products/{pid}/review") //we are not really sending the id but just a place holder for an id. just a path variable.
	// to tell spring from where to take id value
	public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable("pid")int pid) { 
		Product p = service.findById(pid);
		if(p==null) {
			return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
		}
		List<Review> reviews = rev.findByProductId(pid);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
		
	}
	
	@PostMapping("/products/{pid}/review")
	public ResponseEntity<Review> addReviewToProduct(@PathVariable("pid")int productId,@RequestBody Review review){
		try {
			int id = rev.saveReview(review, productId);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation((URI.create("/products/"+productId+"/review/"+id)));
			 return new ResponseEntity<Review>(review, headers, HttpStatus.CREATED);
	    } catch (NoSuchProductException e) {
	      return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
	    }
		
		
	}
	
}
