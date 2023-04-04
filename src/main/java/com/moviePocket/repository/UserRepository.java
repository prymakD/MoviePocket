package com.moviePocket.repository;

import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(String username);
    User findByEmail(String mail);
}
