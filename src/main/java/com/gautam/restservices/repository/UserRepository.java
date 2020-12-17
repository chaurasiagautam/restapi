package com.gautam.restservices.repository;

import com.gautam.restservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository to perform all DAO operation.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  User findByUserName(String userName);
}
