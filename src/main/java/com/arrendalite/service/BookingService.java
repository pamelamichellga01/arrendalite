package com.arrendalite.service;

import com.arrendalite.model.Booking;
import com.arrendalite.model.Property;
import com.arrendalite.repository.BookingRepository;
import com.arrendalite.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;

    public Booking createBooking(Booking booking) {
        validateNotSunday(booking);
        Property property = findPropertyById(booking.getProperty().getId());
        long days = calculateDays(booking);
        double totalPrice = calculateTotalPrice(property.getDailyPrice(), days);

        booking.setTotalPrice(totalPrice);
        booking.setIsCancelled(false);
        return bookingRepository.save(booking);
    }

    private void validateNotSunday(Booking booking) {
        if (booking.getStartDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("No se puede reservar un domingo");
        }
    }

    private Property findPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
    }

    private long calculateDays(Booking booking) {
        return ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
    }

    private double calculateTotalPrice(double dailyPrice, long days) {
        double total = dailyPrice * days;
        if (days > 7) {
            total *= 0.9; // 10% de descuento
        }
        return total;
    }

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        booking.setIsCancelled(true);
        return bookingRepository.save(booking);
    }

    public List<Booking> bookingList() {
        return bookingRepository.findAll();
    }
}
