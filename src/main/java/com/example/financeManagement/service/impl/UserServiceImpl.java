package com.example.financeManagement.service.impl;

import com.example.financeManagement.model.Transaction;
import com.example.financeManagement.model.User;
import com.example.financeManagement.repository.UserRepository;
import com.example.financeManagement.request.UserUpdateRequest;
import com.example.financeManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public User add(User user) {
        Objects.requireNonNull(user, "User cannot be null");

        User newUser = User
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .balance(user.getBalance())
                .transactions(user.getTransactions())
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public User update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        Objects.requireNonNull(userUpdateRequest, "UserUpdateRequest cannot be null");

        user.setUsername(userUpdateRequest.getUsername());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPreviousBalance(user.getBalance());
        user.setBalance(userUpdateRequest.getBalance());

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public User addTransaction(Transaction transaction) {
        return null;
    }
}
