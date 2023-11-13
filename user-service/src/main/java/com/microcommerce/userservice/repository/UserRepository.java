package com.microcommerce.userservice.repository;

import com.microcommerce.userservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.confirmationToken ct WHERE ct.token = :confirmationToken")
    User findUserByConfirmationToken(UUID confirmationToken);
}
