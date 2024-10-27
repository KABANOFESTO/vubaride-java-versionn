package rw.transport.vubaride.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.transport.vubaride.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingCode(String bookingCode);
}
