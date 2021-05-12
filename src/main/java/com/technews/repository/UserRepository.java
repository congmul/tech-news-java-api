package com.technews.repository;

import com.technews.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//A repository in Java is any class that fulfills the role of a data access object (DAO)—in other words,
// it contains data retrieval, storage, and search functionality.
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    // This method will allow us to do what the name implies - find users by their email
    User findUserByEmail(String email) throws Exception;
}
