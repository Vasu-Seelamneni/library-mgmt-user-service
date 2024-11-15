package com.msp.library.mgmt.repository;

import com.msp.library.mgmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Iterable<User> findByNameContainingIgnoreCase(String name);
}
