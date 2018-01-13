package DAOS;

import java.util.ArrayList;

import objects.Band;
import objects.Concert;
import objects.Venue;

public interface ConcertDAO {
		//Create
		public static String createConcert(String date, int rating, String pic, Band band,Venue venue) {
			return null;
		}
		//Read
		public ArrayList<Concert> getAllConcerts();
		public Concert getConcert(int concertID);
		public int getYearCount(int year);
		public ArrayList<Integer> getAllYears();
		//Update
		public void updateDate(String date, int concertId);
		public void updateRating(int newRating, int concertId);
		//Delete
		public void deleteConcert(int concertId);
}
