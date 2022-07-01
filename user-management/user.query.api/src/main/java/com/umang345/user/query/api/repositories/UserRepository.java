package com.umang345.user.query.api.repositories;

import com.umang345.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
