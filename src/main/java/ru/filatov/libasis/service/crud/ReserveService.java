package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.ReserveEntity;
import ru.filatov.libasis.repository.ReservesRepository;

@Service
public class ReserveService {
    ReservesRepository repository;

    @Autowired
    public ReserveService(ReservesRepository repository) {
        this.repository = repository;
    }


    public void create(ReserveEntity reserve) {
        repository.save(reserve);
    }


    public ReserveEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }


    public void update(Integer id, ReserveEntity reserve) {
        repository.save(new ReserveEntity(id, reserve.getBook(), reserve.getUser(),
                reserve.getStartDate(), reserve.getFinishDate(), reserve.getDeadlineStatus()));
    }


    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
