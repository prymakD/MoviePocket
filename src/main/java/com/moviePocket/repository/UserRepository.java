package com.moviePocket.repository;

import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(String username);
    User findByEmail(String mail);

    User findByUsername(String username);

    User findByActivationCode(String code);

    User findByNewEmailToken(String token);

    User findByTokenLostPassword(String token);
}
