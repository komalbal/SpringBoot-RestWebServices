package com.example.sbMicroservice.japsample.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.sbMicroservice.japsample.entity.User;

@Repository
@Transactional
public class UserDaoService {

	private EntityManager entityManager;
	
	public long insert(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
