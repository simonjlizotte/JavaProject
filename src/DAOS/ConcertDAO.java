package DAOS;

import java.util.ArrayList;

import objects.Band;
import objects.Concert;
import objects.Venue;

public interface ConcertDAO {
		//Create
		public static void createConcert(String date, int rating, String pic, Band band,Venue venue) {
		}
		//Read
		public ArrayList<Concert> getAllConcerts();
		public Concert getConcert(int concertID);
		//Update
		public void updateDate(Band band);
		public void updateRating(Band band);
		public void updatePicture(Band band);
		//Delete
		public void deleteConcert(int concertId);
}
