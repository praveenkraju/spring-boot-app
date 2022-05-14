package com.uob.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uob.app.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

	@Query("select u from AppUser u where u.userID = :userID")
	AppUser findByUserID(@Param("userID") String userID);
	
	@Query("select u from AppUser u left join u.addresses where u.userID = :userID")
	AppUser fetchUserDetailsByID(@Param("userID") String userID);
}
