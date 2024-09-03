package com.airbnb.repository;

import com.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {

    Optional<PropertyUser> findByUsername(String username);
}