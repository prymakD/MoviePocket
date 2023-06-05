package com.moviePocket.repository.user;

import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(String username);
    User findByEmail(String mail);
    boolean existsByUsername(String username);
    User findByActivationCode(String code);
    User findByNewEmailToken(String token);
    User findByTokenLostPassword(String token);

    User findAllByUsername(String username);
}
