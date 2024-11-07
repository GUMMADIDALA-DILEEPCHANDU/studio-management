//package com.studio.studio_management.services;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.studio.studio_management.models.BookingRequest;
//import com.studio.studio_management.models.StudioClass;
//import com.studio.studio_management.repositories.InMemoryRepository;
//
//@Service
//public class ClassService {
//
//    private final InMemoryRepository repository;
//
//    public ClassService(InMemoryRepository repository) {
//        this.repository = repository;
//    }
//
//    public void createClass(StudioClass studioClass) {
//        repository.addClass(studioClass);
//    }
//
//
//    public void bookClass(BookingRequest bookingRequest) {
//        // Assuming studioClassDates is a Map<LocalDate, List<String>> where the key is the class date
//        Map<LocalDate, List<String>> studioClassDates = repository.getStudioClassDates();
//
//        // Check if the class exists
//        Optional<StudioClass> optionalClass = repository.getClasses().stream()
//                .filter(c -> c.getClassName().equals(bookingRequest.getClassName()))
//                .findFirst();
//
//        if (optionalClass.isEmpty()) {
//            throw new IllegalArgumentException("Class not found");
//        }
//
//        // Get the StudioClass object
//        StudioClass studioClass = optionalClass.get();
//
//        // Check if the booking date is within the class dates
//        if (bookingRequest.getDate().isBefore(studioClass.getStartDate()) ||
//            bookingRequest.getDate().isAfter(studioClass.getEndDate())) {
//            throw new IllegalArgumentException("Booking date is outside of class schedule");
//        }
//
//        // Check if the name already exists in the booking list for the same class and date
//        List<String> bookedNamesForDate = studioClassDates.get(bookingRequest.getDate());
//
//        if (bookedNamesForDate != null && bookedNamesForDate.contains(bookingRequest.getName())) {
//            throw new IllegalArgumentException("This class is already booked for the specified date by the same person");
//        }
//
//        // Check if the person has any booking on the requested date for a different class
//        List<String> existingBookingsForName = studioClassDates.get(bookingRequest.getDate());
//        if (existingBookingsForName != null && existingBookingsForName.contains(bookingRequest.getName())) {
//            // If the name exists for the same date but a different class, allow booking
//            repository.addBooking(bookingRequest);
//        } else {
//            // No existing booking, so proceed with booking
//            repository.addBooking(bookingRequest);
//        }
//    }
//
//    
//    
//    
//    public List<StudioClass> getAllClasses() {
//        return repository.getClasses();
//    }
//    public Map<LocalDate, List<String>> getAllBookings() {
//        return repository.getBookings();
//    }
//}
//

package com.studio.studio_management.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studio.studio_management.models.BookingRequest;
import com.studio.studio_management.models.StudioClass;
import com.studio.studio_management.repositories.InMemoryRepository;

@Service
public class ClassService {

    private final InMemoryRepository repository;

    public ClassService(InMemoryRepository repository) {
        this.repository = repository;
    }

    public void createClass(StudioClass studioClass) {
        repository.addClass(studioClass);
    }

    public void bookClass(BookingRequest bookingRequest) {
        Map<LocalDate, Map<String, List<String>>> studioClassDates = repository.getStudioClassDates();

        Optional<StudioClass> optionalClass = repository.getClasses().stream()
                .filter(c -> c.getClassName().equals(bookingRequest.getClassName()))
                .findFirst();

        if (optionalClass.isEmpty()) {
            throw new IllegalArgumentException("Class not found");
        }

        StudioClass studioClass = optionalClass.get();

        if (bookingRequest.getDate().isBefore(studioClass.getStartDate()) ||
            bookingRequest.getDate().isAfter(studioClass.getEndDate())) {
            throw new IllegalArgumentException("Booking date is outside of class schedule");
        }

        Map<String, List<String>> bookingsForDate = studioClassDates.get(bookingRequest.getDate());

        if (bookingsForDate != null) {
            List<String> bookedNamesForClass = bookingsForDate.get(bookingRequest.getClassName());
            if (bookedNamesForClass != null && bookedNamesForClass.contains(bookingRequest.getName())) {
                throw new IllegalArgumentException("This class is already booked for the specified date by the same person");
            }

            for (Map.Entry<String, List<String>> entry : bookingsForDate.entrySet()) {
                List<String> namesForClass = entry.getValue();
                if (namesForClass.contains(bookingRequest.getName()) && !entry.getKey().equals(bookingRequest.getClassName())) {
                    repository.addBooking(bookingRequest);
                    return;
                }
            }
        }

        repository.addBooking(bookingRequest);
    }

    public List<StudioClass> getAllClasses() {
        return repository.getClasses();
    }

    public Map<LocalDate, Map<String, List<String>>> getAllBookings() {
        return repository.getStudioClassDates();
    }
}

