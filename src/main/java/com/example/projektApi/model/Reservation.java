package com.example.projektApi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime reservationDate = LocalDateTime.now();
    private boolean active = true;
    private String status = "RESERVED"; // RESERVED, COMPLETED, CANCELED

    public Reservation() {}
    public Reservation(Long id,User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = getReservationDate();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
