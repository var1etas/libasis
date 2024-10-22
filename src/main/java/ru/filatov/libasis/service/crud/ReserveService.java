package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.ReserveEntity;
import ru.filatov.libasis.repository.ReservesRepository;

@Service
public class ReserveService implements EntityService<ReserveEntity> {
    ReservesRepository repository;

    @Autowired
    public ReserveService(ReservesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(ReserveEntity reserve) {
        repository.save(reserve);
    }

    @Override
    public ReserveEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, ReserveEntity reserve) {
        repository.save(new ReserveEntity(id, reserve.getBook(), reserve.getUser(),
                reserve.getStartDate(), reserve.getFinishDate(), reserve.getDeadlineStatus()));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
