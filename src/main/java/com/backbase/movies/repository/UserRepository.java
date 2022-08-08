package com.backbase.movies.repository;

import com.backbase.movies.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String userName);
    boolean existsByUsername(String username);
}
