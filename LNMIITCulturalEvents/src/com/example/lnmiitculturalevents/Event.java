package com.example.lnmiitculturalevents;

public class Event {

	    //private variables
	    int id;
	    String name;
	    String date;
	    String venue;
	    String starting_time;
	    String ending_time;
	    String club;
	    String coordinator;
	    String sub_event;
	    String description;
	    String inventory;
	    String budget;
	    String status;

	    // Empty constructor
	    public Event(){

	    }
	    // constructor
	  

	    public Event(int id, String name, String date, String venue,
				String starting_time, String ending_time, String club,
				String coordinator, String sub_event, String description,
				String inventory, String budget, String status) {
			super();
			this.id = id;
			this.name = name;
			this.date = date;
			this.venue = venue;
			this.starting_time = starting_time;
			this.ending_time = ending_time;
			this.club = club;
			this.coordinator = coordinator;
			this.sub_event = sub_event;
			this.description = description;
			this.inventory = inventory;
			this.budget = budget;
			this.status = status;
		}
		// constructor
		public Event(String name, String date, String venue,
				String starting_time, String ending_time, String club,
				String coordinator, String sub_event, String description,
				String inventory, String budget, String status) {
			super();
			this.name = name;
			this.date = date;
			this.venue = venue;
			this.starting_time = starting_time;
			this.ending_time = ending_time;
			this.club = club;
			this.coordinator = coordinator;
			this.sub_event = sub_event;
			this.description = description;
			this.inventory = inventory;
			this.budget = budget;
			this.status = status;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getVenue() {
			return venue;
		}
		public void setVenue(String venue) {
			this.venue = venue;
		}
		public String getStarting_time() {
			return starting_time;
		}
		public void setStarting_time(String starting_time) {
			this.starting_time = starting_time;
		}
		public String getEnding_time() {
			return ending_time;
		}
		public void setEnding_time(String ending_time) {
			this.ending_time = ending_time;
		}
		public String getClub() {
			return club;
		}
		public void setClub(String club) {
			this.club = club;
		}
		public String getCoordinator() {
			return coordinator;
		}
		public void setCoordinator(String coordinator) {
			this.coordinator = coordinator;
		}
		public String getSub_event() {
			return sub_event;
		}
		public void setSub_event(String sub_event) {
			this.sub_event = sub_event;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getInventory() {
			return inventory;
		}
		public void setInventory(String inventory) {
			this.inventory = inventory;
		}
		public String getBudget() {
			return budget;
		}
		public void setBudget(String budget) {
			this.budget = budget;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
	
}
