package com.msp.library.mgmt.service.impl;

import com.msp.library.mgmt.model.User;
import com.msp.library.mgmt.repository.UserRepository;
import com.msp.library.mgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean isUserExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }
}
