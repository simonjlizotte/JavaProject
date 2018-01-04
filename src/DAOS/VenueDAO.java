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
		public Venue getVenue(String venueName);
		//Update
		public void updateVenue(Venue venue);
		public void updateCity(Venue venue);
		//Delete
		public void deleteVenue(Venue venue);
}
