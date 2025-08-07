package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    Optional<User> findById(Long userId);
    void delete(Long userId);
    List<User> findAll();
    boolean checkEmail(String email);
    boolean removeEmail(String email);
    boolean addEmail(String email);
}
