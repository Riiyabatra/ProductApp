package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewDAO {

	Review findByID(int id);

	//Review save(Review toBeSaved);

	void deleteById(int id);

	List<Review> findAll();

	Review save(Review toBeSaved);

	List<Review> findByProductId(int product_id);

	
}