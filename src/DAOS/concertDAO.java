package DAOS;

import java.util.ArrayList;

import objects.Band;
import objects.Concert;
import objects.Genre;
import objects.Venue;

public interface concertDAO {
		//Create
		public static void createConcert(String date, int rating, String pic, Band band,Venue venue) {
		}
		//Read
		public ArrayList<Concert> getAllConcerts();
		public Band getConcert(int concertID);
		//Update
		public void updateDate(Band band);
		public void updateRating(Band band);
		public void updatePicture(Band band);
		//Delete
		public void deleteBand(Band band);
}
