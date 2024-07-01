package wegennetwerk;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getVertrekkendeWegen().stream().allMatch(w -> w != null && w.getStartkruispunt() == this)
 * @invar | getAankomendeWegen().stream().allMatch(w -> w != null && w.getEindkruispunt() == this)
 */
public class Kruispunt {
	
	/**
	 * @invar | vertrekkendeWegen != null
	 * @invar | aankomendeWegen != null
	 * @invar | vertrekkendeWegen.stream().allMatch(w -> w != null && w.startkruispunt == this)
	 * @invar | aankomendeWegen.stream().allMatch(w -> w != null && w.eindkruispunt == this)
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Weg> vertrekkendeWegen = new HashSet<>();
	
	/**
	 * @representationObject
	 * @peerObjects
	 */
	Set<Weg> aankomendeWegen = new HashSet<>();

	/**
	 * @post | result != null
	 * 
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Weg> getVertrekkendeWegen() {
		return Set.copyOf(vertrekkendeWegen);
	}
	
	/**
	 * @post | result != null
	 * 
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Weg> getAankomendeWegen() {
		return Set.copyOf(aankomendeWegen);
	}
	
	/**
	 * @post | getVertrekkendeWegen().isEmpty()
	 * @post | getAankomendeWegen().isEmpty()
	 */
	public Kruispunt() {}
	
}
