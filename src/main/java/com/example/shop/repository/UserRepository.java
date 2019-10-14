package com.example.shop.repository;

import com.example.shop.been.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(Integer id);

    User findByUsername(String name);
}
