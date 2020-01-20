package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewService {
	public int saveReview(Review toBeAdded, int productId);
	//void removeReview(int id);
	public List<Review> findByProductId(int product_id);
	
}
