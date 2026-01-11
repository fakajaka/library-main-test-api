package com.example.projektApi.service;


import com.example.projektApi.model.Book;
import com.example.projektApi.model.Reservation;
import com.example.projektApi.repository.BookRepository;
import com.example.projektApi.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;

    public BookService(BookRepository bookRepository, ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setYear(book.getYear());
        existingBook.setStatus(book.getStatus());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        List<Reservation> reservations = reservationRepository.findByBookId(id);
        reservationRepository.deleteAll(reservations);
        bookRepository.deleteById(id);
    }
}
