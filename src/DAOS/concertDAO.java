package DAOS;

import java.util.ArrayList;

import objects.Band;
import objects.Concert;
import objects.Genre;

public interface ConcertDAO {
		//Create
		public void createConcert(Concert concert, Band band, Genre genre);
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
