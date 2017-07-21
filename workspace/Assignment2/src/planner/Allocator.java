package planner;

import java.util.*;

/**
 * Provides a method for finding a safe allocation of events to venues.
 */
public class Allocator {

	/**
	 * <p>
	 * Returns a safe allocation of events to venues, if there is at least one
	 * possible safe allocation, or null otherwise.
	 * </p>
	 * 
	 * <p>
	 * NOTE: What it means for an allocation of events to venues to be safe is
	 * defined in the assignment handout.
	 * </p>
	 * 
	 * @require events != null && venues != null && !events.contains(null) &&
	 *          !venues.contains(null) && events does not contain duplicate
	 *          events && venues does not contain duplicate venues.
	 * @ensure Returns a safe allocation of events to venues, if there is at
	 *         least one possible safe allocation, or null otherwise.
	 */
	public static Map<Event, Venue> allocate(List<Event> events,
			List<Venue> venues) {
		Set<Map<Event, Venue>> allocations = allocations(events, venues);
		
		if (allocations.isEmpty()) {
			return null;
		} else {
			return allocations.iterator().next();
		}
	}

	/**
	 * Returns the set of all possible safe allocations of events to venues.
	 * 
	 * @require events != null && venues != null && !events.contains(null) &&
	 *          !venues.contains(null) && events does not contain duplicate
	 *          events && venues does not contain duplicate venues.
	 * @ensure Returns the set of all possible safe allocations of events to
	 *         venues. (Note: if there are no possible allocations, then this
	 *         method should return an empty set of allocations.)
	 */
	private static Set<Map<Event, Venue>> allocations(List<Event> events,
			List<Venue> venues) {
		if(events != null && venues != null && events.contains(null) &&
				venues.contains(null)){
			return	Collections.emptySet();
		}
		if(hasDuplicateItem(events) || hasDuplicateItem(venues)) {
			return	Collections.emptySet();
		}
		Set<Map<Event, Venue>> set = new HashSet<Map<Event, Venue>>();
		List<Allocation> validAllocations = getAllValidAllocation(events, venues);

		for (int i = 0; i<validAllocations.size(); i++) {
			Map<Event, Venue> map = new HashMap<Event, Venue>();
			map.put(validAllocations.get(i).event, validAllocations.get(i).venue);
			
			List<Allocation> allocationsTaken = new ArrayList<Allocation>();
			allocationsTaken.add(validAllocations.get(i));
			for (int j = 0; j<validAllocations.size(); j++) {
				Allocation currentAllocation = validAllocations.get(j);
				if(!allocationsTaken.contains(currentAllocation)) {
					map.put(currentAllocation.event, currentAllocation.venue);
					allocationsTaken.add(currentAllocation);
				}
			}
			if(map.size() == events.size()) {
				set.add(map);
			}
		}
		
		return set; 
	}
	/**
	 * Returns true if the list has dupolicate values, returns false otherwise
	 * 
	 */
	private static <T>  boolean hasDuplicateItem(List<T> list) {
		Set<T> uniques = new HashSet<T>(list);    	

		return list.size() != uniques.size();
	}

	private static List<Allocation> getAllValidAllocation(List<Event> events, List<Venue> venues) {
		List<Allocation> allocations = new ArrayList<Allocation>();
		for (Venue venue : venues) {
			for (Event event : events) {
				if(venue.canHost(event)) {
					
					Allocation newAllocation = new Allocation(event, venue);
					
					allocations.add(newAllocation);
				}
			}
			
		}
		return allocations;
	} 
}

class Allocation {
	Event event;
	Venue venue;
	public Allocation(Event e, Venue v) {
		this.event = e;
		this.venue = v;
	}

	@Override
	public boolean equals(Object object ) {
		if (!(object instanceof Allocation)) {
			return false;
		}
		Allocation other = (Allocation) object; // the Allocation to compare
		return this.event.equals(other.event) || this.venue.equals(other.venue);
	}
}
