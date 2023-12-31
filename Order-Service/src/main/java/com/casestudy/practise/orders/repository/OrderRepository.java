package com.casestudy.practise.orders.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.practise.orders.model.Orders;



@Repository
public interface OrderRepository  extends MongoRepository<Orders, Integer>{
	List<Orders> findByCustomerId(Integer customerId);
	List<Orders> findByFullName(String fullName);
	String findByEmail(String email);

	Orders findFirstByOrderByOrderIdDesc();
}