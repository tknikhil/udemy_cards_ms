package com.ultimate.cards.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.cards.model.Cards;

public interface CardRepository extends CrudRepository<Cards, Long>{
	List<Cards> findByCustomerId(int customerId);
}	
