package com.microcommerce.userservice.repository;

import com.microcommerce.userservice.data.entity.Role;
import com.microcommerce.userservice.data.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(Authority authority);
}
