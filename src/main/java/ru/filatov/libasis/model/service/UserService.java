package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.dto.UserRequestDto;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findByLogin(userRequestDto.login());
        if (user != null) {
            return false;
        }
        UserEntity userEntity = new UserEntity(userRequestDto.name(), userRequestDto.login(),
                passwordEncoder.encode(userRequestDto.password()));
        userRepository.save(userEntity);
        return true;
    }

    public UserEntity getUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }

    public boolean updateUser(UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findByLogin(userRequestDto.login());
        if (user == null) {
            return false;
        }
        UserEntity userEntity = new UserEntity(userRequestDto.name(), userRequestDto.login(),
                passwordEncoder.encode(userRequestDto.password()));
        userRepository.save(userEntity);
        return true;
    }

    public boolean deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
