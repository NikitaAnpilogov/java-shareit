package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Collection<UserDto> findAll() {
        log.debug("Поиск всех пользователей");
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .toList();
    }

    public UserDto findById(long userId) {
        log.debug("Поиск пользователя с ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        return UserMapper.toUserDto(user);
    }

    public UserDto create(User user) {
        log.debug("Создание пользователя {}", user);
        User newUser = userRepository.save(user);
        return UserMapper.toUserDto(newUser);
    }

    public UserDto update(long userId, User user) {
        log.debug("Обновление пользователя с ID: {}", userId);
        User updUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        if (user.getName() != null) {
            updUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            updUser.setEmail(user.getEmail());
        }
        userRepository.save(updUser);
        return UserMapper.toUserDto(updUser);
    }

    public void deleteUserById(long userId) {
        log.debug("Удаление пользователя с ID: {}", userId);
        User userForDelete = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        userRepository.delete(userForDelete);
    }
}
