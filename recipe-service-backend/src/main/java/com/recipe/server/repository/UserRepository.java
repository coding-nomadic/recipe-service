package com.recipe.server.repository;


import com.recipe.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param username
     * @return
     */
    Optional<User> findByUserName(String username);

    /**
     * @param username
     * @return
     */
    Boolean existsByUserName(String username);

    /**
     * @param email
     * @return
     */

    Boolean existsByEmail(String email);

    /**
     * @param token
     * @return
     */

    Boolean existsByToken(String token);

    /**
     * @param token
     * @return
     */
    Optional<User> findByToken(String token);
}