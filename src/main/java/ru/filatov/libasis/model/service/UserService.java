package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.entity.Role;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.repository.UserRepository;

import javax.naming.NameAlreadyBoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы с пользователями
 */
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Создание пользователя в системе
     */
    public UserEntity createUser(UserEntity user) throws NameAlreadyBoundException {
        Optional<UserEntity> existUser = userRepository.findByLogin(user.getLogin());
        if (existUser.isPresent()) {
            throw new NameAlreadyBoundException("Username already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setStatistic(100.0f);
        user.setReservesCount(0L);
        user.setStatus(true);
        return userRepository.save(user);
    }

    /**
     * Получение пользователя по логину
     */
    public Optional<UserEntity> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * Удаление пользователя из системы
     */
    public void deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

    /**
     * Получение всех пользователей в системе
     */
    public List<UserEntity> getAllUsers() {
        return userRepository.getAll();
    }

    /**
     * Изменение статуса (активация/деактивация) аккаунта пользователя в системе
     */
    public void changeUserStatus(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        user.setStatus(!user.getStatus());
        userRepository.save(user);
    }
    /**
     * Изменение роли (пользователь/администратор) аккаунта пользователя в системе
     */
    public void changeUserRole(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if(user.getRole() == Role.ROLE_ADMIN) {
            user.setRole(Role.ROLE_USER);
        } else {
            user.setRole(Role.ROLE_ADMIN);
        }
        userRepository.save(user);
    }
}
