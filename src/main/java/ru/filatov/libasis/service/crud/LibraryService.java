package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.filatov.libasis.entity.LibraryEntity;
import ru.filatov.libasis.repository.LibraryRepository;

@Service
public class LibraryService implements EntityService<LibraryEntity> {
    LibraryRepository repository;

    @Autowired
    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(LibraryEntity libraryLot) {
        repository.save(libraryLot);
    }

    @Override
    public LibraryEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, LibraryEntity libraryLot) {
        repository.save(new LibraryEntity(id, libraryLot.getBook(),libraryLot.getResponsible(),
                libraryLot.getAddDate(), libraryLot.getInStockCount(), libraryLot.getRate()));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
