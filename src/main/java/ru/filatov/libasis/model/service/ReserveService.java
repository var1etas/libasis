package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.entity.ReserveEntity;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.repository.BookRepository;
import ru.filatov.libasis.model.repository.ReserveRepository;
import ru.filatov.libasis.model.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с резервациями
 */
@Service
public class ReserveService {

    private ReserveRepository reserveRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public ReserveService(ReserveRepository reserveRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reserveRepository = reserveRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /**
     * Резервация (бронирование) книги
     */
    public boolean reserveBook(Long bookId, String username) {
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        UserEntity userEntity = userRepository.findByLogin(username).get();
        if(!userEntity.getStatus()){
            return false;
        }
        if(!bookEntity.getStatus()){
            return false;
        }
        ReserveEntity reserveEntity = new ReserveEntity(bookEntity, userEntity,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(1L), false, false);
        bookEntity.setStatus(false);
        userEntity.setReservesCount(userEntity.getReservesCount() + 1);
        userRepository.save(userEntity);
        bookRepository.save(bookEntity);
        reserveRepository.save(reserveEntity);
        return true;
    }

    /**
     * Возврат книги с обновлением статистики
     */
    public boolean returnBook(Long bookId, String username) {
        ReserveEntity reserveEntity = reserveRepository.findByBookIdAndUserLoginAndIsReturned(bookId, username, false);
        UserEntity userEntity = userRepository.findByLogin(username).get();
        if(reserveEntity.getReturned() && userEntity.getId() != reserveEntity.getUser().getId()){
            return false;
        }
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        reserveEntity.setReturned(true);
        bookEntity.setStatus(true);
        boolean missedDeadlineStatus = LocalDateTime.now().isAfter(reserveEntity.getFinishDate());
        if(!reserveEntity.getDeadlineStatus()) {
            if(!missedDeadlineStatus) {
                userEntity.setStatistic(100f / userEntity.getReservesCount()
                        * ((userEntity.getReservesCount() - 1f) / 100f * userEntity.getStatistic() + 1f));
            } else {
                userEntity.setStatistic(100f / userEntity.getReservesCount()
                        * ((userEntity.getReservesCount() - 1f) / 100f * userEntity.getStatistic()));
                reserveEntity.setDeadlineStatus(true);
            }
        }
        reserveRepository.save(reserveEntity);
        bookRepository.save(bookEntity);
        userRepository.save(userEntity);
        return true;
    }

    /**
     * Возвращает все резервации заданного пользователя
     */
    public List<ReserveEntity> getReservesByUsername(String username) {
        return reserveRepository.findAllByUserLoginAndIsReturned(username, false);
    }

    /**
     * Возвращает все резервации
     */
    public List<ReserveEntity> getAllReserves(){
        return reserveRepository.getAll();
    }

    /**
     * Обновление статусов просроченных дедлайнов и пользовательской статистики в заданное время
     */
    @Scheduled(cron = "${deadline.status.update.cron}")
    public void updateMissedDeadlines() {
        LocalDateTime now = LocalDateTime.now();

        List<ReserveEntity> activeReservations = reserveRepository.findNotReturned();

        for (ReserveEntity reservation : activeReservations) {
            if (reservation.getFinishDate().isBefore(now)) {
                reservation.setDeadlineStatus(true);
                UserEntity user = reservation.getUser();
                user.setStatistic(100f / user.getReservesCount()
                        * ((user.getReservesCount() - 1f) / 100f * user.getStatistic()));
                userRepository.save(user);
                reserveRepository.save(reservation);
            }
        }
    }
}
