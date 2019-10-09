package com.example.shop.repository;

import com.example.shop.been.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findById(Integer id);

    User findByUsername(String name);
}
