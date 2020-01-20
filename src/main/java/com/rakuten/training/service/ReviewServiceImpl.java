package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDAO dao;
	
	@Autowired
	ProductDAO productDAO;
	public void setDao(ReviewDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int saveReview(Review toBeAdded, int productId) {
		Product product = productDAO.findById(productId);
		if(product==null) {
			throw new NoSuchProductException();
		}
		toBeAdded.setProduct(product);
		Review added = dao.save(toBeAdded);
		return added.getId();
	}
	@Override
	public List<Review> findByProductId(int product_id) {
		
		return dao.findByProductId(product_id);
	}

	
}
