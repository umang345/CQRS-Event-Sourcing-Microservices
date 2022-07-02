package com.umang345.user.oAuth20.repositories;

import com.umang345.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'account.username' : ?0 }")
    Optional<User> findByUsername(String username);
}
