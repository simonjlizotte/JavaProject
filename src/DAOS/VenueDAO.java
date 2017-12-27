package DAOS;

import java.util.ArrayList;

import objects.Venue;

public interface VenueDAO {
		//Create
		public void createGenre(Venue venue);
		//Read
		public ArrayList<Venue> getAllVenues();
		public Venue getVenue(int venueID);
		//Update
		public void updateVenue(Venue venue);
		public void updateCity(Venue venue);
		//Delete
		public void deleteVenue(Venue venue);
}
