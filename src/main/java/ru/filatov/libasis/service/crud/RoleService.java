package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.RoleEntity;
import ru.filatov.libasis.repository.RoleRepository;

@Service
public class RoleService implements EntityService<RoleEntity> {
    RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(RoleEntity role) {
        repository.save(role);
    }

    @Override
    public RoleEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, RoleEntity role) {
        repository.save(new RoleEntity(id, role.getName(),
                role.getActivityStatus(), role.getDescription()));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
