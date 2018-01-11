package confirmationMessage;

import javafx.scene.Scene;
import tables.ConcertTable;

public class DeleteMessageScene extends Scene{

	public DeleteMessageScene(String bandName, String date, int concertId, ConcertTable concertTable) {
		super(new DeleteMessagePane(bandName, date, concertId, concertTable), 500, 200);
	}

}
