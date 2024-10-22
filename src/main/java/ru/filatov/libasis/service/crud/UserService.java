package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.UserEntity;
import ru.filatov.libasis.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements EntityService<UserEntity> {
    @Autowired
    UserRepository repository;

    public List<UserEntity> findByRole(String role) {
        return repository.findByRole(role);
    }

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(UserEntity user) {
        repository.save(user);
    }

    @Override
    public UserEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, UserEntity user) {
        repository.save(new UserEntity(id, user.getRole(), user.getName(),
                user.getEmail(), user.getPhoneNumber()));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
