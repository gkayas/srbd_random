package planner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestMain {
	public static void main(String [] args) throws IOException, FormatException {
		List<Event> events = new ArrayList<Event>();
		events.add(new Event("E1", 60));
		events.add(new Event("E2", 100));
		events.add(new Event("E3", 10));
		 
		List<Venue> venues = VenueReader.read(
		         "read_03_correctlyFormatted_manyVenues.txt");
	
		
		Map<Event, Venue> map = Allocator.allocate(events, venues);
//		Set<Event> eventSet = map.keySet();
//		for (Event event: eventSet) {
//			System.out.println(event +" : "+map.get(event).getCapacity());
//		}
	}
}
