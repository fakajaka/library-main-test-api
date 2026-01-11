package com.example.projektApi.repository;

import com.example.projektApi.model.Reservation;
import com.example.projektApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByBookId(Long bookId);

    @Query("SELECT r.book, COUNT(r) as reservationCount FROM Reservation r GROUP BY r.book ORDER BY reservationCount DESC")
    List<Object[]> findMostReservedBooks();
}
