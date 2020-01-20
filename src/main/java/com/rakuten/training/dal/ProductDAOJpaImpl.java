package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.training.domain.Product;

@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO{
	@Autowired //can apply on object but prefer to write setter.
	EntityManager em;
	
	@Override
	public Product save(Product toBeSaved) {
		em.persist(toBeSaved);
		// TODO Auto-generated method stub
		return toBeSaved; //before the object is returned to service layer transaction will be completed as transactional is present only in DAO layer.
		// if want to receive object in persistent state then put transactional in service layer.
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		Product p = em.find(Product.class, id);
		//System.out.println("This product has " +p.getReviews().size()+"reviews");
		return p;

	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("select p from Product as p");
		List<Product> all = q.getResultList();
		return all;
	}

	@Override
	public void deleteById(int id) {
		//Product p = em.find(Product.class, id);//find only to get the object in managed state.
		Product p = em.getReference(Product.class, id); //it gets just id. 
		em.remove(p);
		//***cascade remove doesn't work with query. It should be executed with remove.***
		/*Query q = em.createQuery("delete from Product as p where p.id = :idParam");
		q.setParameter("idParam", id);
		q.executeUpdate();*/
	}
	

}
