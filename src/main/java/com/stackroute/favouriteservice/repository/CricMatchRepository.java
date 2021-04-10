package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.favouriteservice.domain.Cricket;

public interface CricMatchRepository extends JpaRepository<Cricket, Integer> {
	
	List<Cricket> findByUserId (String userId);

}
