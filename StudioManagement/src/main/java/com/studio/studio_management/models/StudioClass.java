package com.studio.studio_management.models;



import java.time.LocalDate;


public class StudioClass {
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    
    public StudioClass() {
    	
    }
    
	public StudioClass(String className, LocalDate startDate, LocalDate endDate, int capacity) {
		super();
		this.className = className;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
    
    
    
}
