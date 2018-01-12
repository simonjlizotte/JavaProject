package DAOS;

import java.util.ArrayList;

import objects.Venue;

public interface VenueDAO {
		//Create
		public static void createVenue(Venue venue) {
		}
		//Read
		public static ArrayList<Venue> getAllVenues() {
			return null;
		}
		public Venue getVenue(int venueId);
		//Update
		public void updateVenue(int venueID, String newVenue);
		public void updateCity(int venueID, String newCity);
		//Delete
		public void deleteVenue(Venue venue);
}
