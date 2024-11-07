package com.studio.studio_management.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studio.studio_management.models.BookingRequest;
import com.studio.studio_management.models.StudioClass;
import com.studio.studio_management.services.ClassService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/classes")
    public ResponseEntity<String> createClass(@Valid @RequestBody StudioClass studioClass) {
        classService.createClass(studioClass);
        return ResponseEntity.ok("Class created successfully");
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookClass(@Valid @RequestBody BookingRequest bookingRequest) {
        classService.bookClass(bookingRequest);
        return ResponseEntity.ok("Class booked successfully");
    }

    @GetMapping("/classes")
    public List<StudioClass> getAllClasses() {
        return classService.getAllClasses();
    }
    
    @GetMapping("/bookings")
    public Map<LocalDate, Map<String, List<String>>> getAllBookings() {
        return classService.getAllBookings();
    }
}

