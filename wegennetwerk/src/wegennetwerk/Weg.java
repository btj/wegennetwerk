package wegennetwerk;

import java.util.Set;
import java.util.stream.Collectors;

import logicalcollections.LogicalSet;

/**
 * @invar | getStartkruispunt() == null || getStartkruispunt().getVertrekkendeWegen().contains(this)
 * @invar | getEindkruispunt() == null || getEindkruispunt().getAankomendeWegen().contains(this)
 */
public class Weg {
	
	/**
	 * @invar | true
	 * @invar | startkruispunt == null || startkruispunt.vertrekkendeWegen.contains(this)
	 * @invar | eindkruispunt == null || eindkruispunt.aankomendeWegen.contains(this)
	 * 
	 * @peerObject
	 */
	Kruispunt startkruispunt;
	
	/**
	 * @peerObject
	 */
	Kruispunt eindkruispunt;
	
	/**
	 * @peerObject
	 */
	public Kruispunt getStartkruispunt() {
		return startkruispunt;
	}

	/**
	 * @peerObject
	 */
	public Kruispunt getEindkruispunt() {
		return eindkruispunt;
	}
	
	/**
	 * @post | getStartkruispunt() == null
	 * @post | getEindkruispunt() == null
	 */
	public Weg() {}

	/**
	 * @throws IllegalStateException | getStartkruispunt() != null
	 * @throws IllegalArgumentException | kruispunt == null
	 * @mutates_properties | getStartkruispunt(), kruispunt.getVertrekkendeWegen()
	 * @post | getStartkruispunt() == kruispunt
	 * @post | kruispunt.getVertrekkendeWegen().equals(LogicalSet.plus(old(kruispunt.getVertrekkendeWegen()), this))
	 */
	public void setStartkruispunt(Kruispunt kruispunt) {
		if (startkruispunt != null)
			throw new IllegalStateException("Deze weg heeft al een startkruispunt");
		if (kruispunt == null)
			throw new IllegalArgumentException("`kruispunt` is null");
		
		startkruispunt = kruispunt;
		kruispunt.vertrekkendeWegen.add(this);
	}
	
	/**
	 * @pre | getStartkruispunt() != null
	 * @mutates_properties | getStartkruispunt(), getStartkruispunt().getVertrekkendeWegen()
	 * @post | getStartkruispunt() == null
	 * @post | old(getStartkruispunt()).getVertrekkendeWegen().equals(LogicalSet.minus(old(getStartkruispunt().getVertrekkendeWegen()), this))
	 */
	public void clearStartkruispunt() {
		startkruispunt.vertrekkendeWegen.remove(this);
		startkruispunt = null;
	}
	
	/**
	 * @pre | getEindkruispunt() == null
	 * @pre | kruispunt != null
	 * @mutates_properties | getEindkruispunt(), kruispunt.getAankomendeWegen()
	 * @post | getEindkruispunt() == kruispunt
	 * @post | kruispunt.getAankomendeWegen().equals(LogicalSet.plus(old(kruispunt.getAankomendeWegen()), this))
	 */
	public void setEindkruispunt(Kruispunt kruispunt) {
		eindkruispunt = kruispunt;
		kruispunt.aankomendeWegen.add(this);
	}
	
	/**
	 * @pre | getEindkruispunt() != null
	 * @mutates_properties | getEindkruispunt(), getEindkruispunt().getAankomendeWegen()
	 * @post | getEindkruispunt() == null
	 * @post | old(getEindkruispunt()).getAankomendeWegen().equals(LogicalSet.minus(old(getEindkruispunt().getAankomendeWegen()), this))
	 */
	public void clearEindkruispunt() {
		eindkruispunt.aankomendeWegen.remove(this);
		eindkruispunt = null;
	}
	
	public Set<Weg> getVolgendeWegenVanTweedeOrde() {
		return eindkruispunt == null ?
				Set.of()
			:
				eindkruispunt.vertrekkendeWegen.stream().flatMap(w ->
					w.eindkruispunt == null ?
						null
					:
						w.eindkruispunt.vertrekkendeWegen.stream()).collect(Collectors.toSet());
	}
	
}
