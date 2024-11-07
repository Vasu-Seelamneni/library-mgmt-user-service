package com.msp.library.mgmt.service;

import com.msp.library.mgmt.model.User;

public interface UserService {

    User save(User user);

    Iterable<User> findByName(String name);

    Iterable<User> getAllUsers();

    void deleteById(Long id);

    User findById(Long id);

    Boolean isUserExists(Long userId);

}
