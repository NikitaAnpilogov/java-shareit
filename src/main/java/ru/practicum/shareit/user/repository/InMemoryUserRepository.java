package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository{
    private final Set<String> emails = new HashSet<>();
    private final Map<Long, User> users = new HashMap<>();
    private Long idCounter = 1L;

    public boolean checkEmail(String email) {
        return emails.contains(email);
    }

    @Override
    public User save(User user) {
        user.setId(idCounter++);
        users.put(user.getId(), user);
        emails.add(user.getEmail());
        return user;
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public void delete(Long userId) {
        users.remove(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean removeEmail(String email) {
        return emails.remove(email);
    }

    @Override
    public boolean addEmail(String email) {
        return emails.add(email);
    }
}
