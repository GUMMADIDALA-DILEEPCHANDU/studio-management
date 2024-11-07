package com.studio.studio_management.models;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {
    @NotBlank
    private String name;
    
    @NotNull
    private LocalDate date;
    


    @NotBlank
    private String className;


    
    public BookingRequest() {
    	
    }

	public BookingRequest(@NotBlank String name, @NotNull LocalDate date) {
		super();
		this.name = name;
		this.date = date;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    
    
}
