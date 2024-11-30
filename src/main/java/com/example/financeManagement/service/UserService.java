package com.example.financeManagement.service;

import com.example.financeManagement.model.Transaction;
import com.example.financeManagement.model.User;
import com.example.financeManagement.request.UserUpdateRequest;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User add(User user);
    User update(Long id, UserUpdateRequest userUpdateRequest);
    void delete(Long id);
    User addTransaction(Transaction transaction);
}
