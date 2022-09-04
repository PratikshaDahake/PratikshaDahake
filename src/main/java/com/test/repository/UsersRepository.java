package com.test.repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Users;


@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {

	Users findByEmailidAndPassword(String emailid, String password);

	
	
}
