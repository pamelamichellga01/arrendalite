package com.arrendalite.service;

import com.arrendalite.exception.BookingNotFoundException;
import com.arrendalite.exception.PropertyNotFoundException;
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

    // Crear una nueva reserva
    public Booking createBooking(Booking booking) {
        validateNotSunday(booking);
        validateBookingDates(booking);

        Property property = findPropertyById(booking.getProperty().getId());
        long days = calculateDays(booking);
        double totalPrice = calculateTotalPrice(property.getDailyPrice(), days);

        booking.setTotalPrice(totalPrice);
        booking.setIsCancelled(false);

        return bookingRepository.save(booking);
    }

    // Cancelar una reserva
    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Reserva no encontrada"));
        booking.setIsCancelled(true);
        return bookingRepository.save(booking);
    }

    // Listar todas las reservas
    public List<Booking> bookingList() {
        return bookingRepository.findAll();
    }

    // -------------------- Métodos privados --------------------

    // Validar que la reserva no inicie en domingo
    private void validateNotSunday(Booking booking) {
        if (booking.getStartDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("No se puede reservar un domingo");
        }
    }

    // Validar que las fechas no se solapen con otras reservas activas
    private void validateBookingDates(Booking booking) {
        List<Booking> bookings = bookingRepository.findAll();
        boolean fechaOcupada = bookings.stream()
                .filter(b -> !b.getIsCancelled())
                .anyMatch(b -> datesOverlap(b, booking));

        if (fechaOcupada) {
            throw new IllegalArgumentException("Las fechas seleccionadas ya están ocupadas");
        }
    }

    // Comprobar si dos reservas se solapan
    private boolean datesOverlap(Booking existing, Booking nuevo) {
        return !nuevo.getEndDate().isBefore(existing.getStartDate()) &&
                !nuevo.getStartDate().isAfter(existing.getEndDate());
    }

    // Buscar propiedad por id
    private Property findPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException("Propiedad no encontrada"));
    }

    // Calcular días de la reserva
    private long calculateDays(Booking booking) {
        return ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
    }

    // Calcular precio total con descuento si aplica
    private double calculateTotalPrice(double dailyPrice, long days) {
        double total = dailyPrice * days;
        if (days > 7) {
            total *= 0.9;
        }
        return total;
    }
}
