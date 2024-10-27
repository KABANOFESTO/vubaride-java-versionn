package rw.transport.vubaride.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.transport.vubaride.repository.BookingRepository;
import rw.transport.vubaride.model.Booking;


import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Optional<Booking> getBookingByCode(String bookingCode) {
        return Optional.ofNullable(bookingRepository.findByBookingCode(bookingCode));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

