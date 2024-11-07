//package com.studio.studio_management.repositories;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import org.springframework.stereotype.Repository;
//
//import com.studio.studio_management.models.BookingRequest;
//import com.studio.studio_management.models.StudioClass;
//
//@Repository
//public class InMemoryRepository {
//
//    private final List<StudioClass> classes = new ArrayList<>();
//   // private final List<BookingRequest> booking = new ArrayList<>();
//    private final ConcurrentMap<LocalDate, List<String>> bookings = new ConcurrentHashMap<>();
//
//    public void addClass(StudioClass studioClass) {
//        classes.add(studioClass);
//    }
//
//    public void addBooking(BookingRequest bookingRequest) {
//        bookings.computeIfAbsent(bookingRequest.getDate(), k -> new ArrayList<>())
//                .add(bookingRequest.getName());
//    }
//    
//    // Method to get all the bookings for a particular class (all dates)
//    public Map<LocalDate, List<String>> getStudioClassDates() {
//        return bookings;
//    }
//    
//    public List<String> getBookingsByDate(LocalDate date) {
//        return bookings.getOrDefault(date, new ArrayList<>());
//    }
//
//    public List<StudioClass> getClasses() {
//        return classes;
//    }
//    
//    public Map<LocalDate, List<String>> getBookings() {
//        return bookings;
//    }
//}
//

package com.studio.studio_management.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.studio.studio_management.models.BookingRequest;
import com.studio.studio_management.models.StudioClass;

@Repository
public class InMemoryRepository {

    private final List<StudioClass> classes = new ArrayList<>();
    private final ConcurrentMap<LocalDate, Map<String, List<String>>> bookings = new ConcurrentHashMap<>();

    public void addClass(StudioClass studioClass) {
        classes.add(studioClass);
    }

    public void addBooking(BookingRequest bookingRequest) {
        bookings.computeIfAbsent(bookingRequest.getDate(), k -> new ConcurrentHashMap<>())
                .computeIfAbsent(bookingRequest.getClassName(), k -> new ArrayList<>())
                .add(bookingRequest.getName());
    }

    // Method to get all the bookings for a particular class (all dates)
    public Map<LocalDate, Map<String, List<String>>> getStudioClassDates() {
        return bookings;
    }

    public List<String> getBookingsByDate(LocalDate date) {
        return bookings.getOrDefault(date, new ConcurrentHashMap<>()).values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public List<StudioClass> getClasses() {
        return classes;
    }
}

