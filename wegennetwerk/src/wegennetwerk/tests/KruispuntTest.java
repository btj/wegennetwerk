package wegennetwerk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import wegennetwerk.Kruispunt;
import wegennetwerk.Weg;

class KruispuntTest {
	
	Kruispunt naamsepoort = new Kruispunt();
	Weg kardinaalMercierlaan = new Weg();
	Kruispunt kantineplein = new Kruispunt();

	@Test
	void testKruispuntConstructor() {
		assertEquals(Set.of(), naamsepoort.getVertrekkendeWegen());
		assertEquals(Set.of(), naamsepoort.getAankomendeWegen());
	}
	
	@Test
	void testWegConstructor() {
		assertNull(kardinaalMercierlaan.getStartkruispunt());
		assertNull(kardinaalMercierlaan.getEindkruispunt());
	}
	
	@Test
	void testSetStartkruispunt() {
		kardinaalMercierlaan.setStartkruispunt(naamsepoort);
		assertSame(naamsepoort, kardinaalMercierlaan.getStartkruispunt());
		assertEquals(Set.of(kardinaalMercierlaan), naamsepoort.getVertrekkendeWegen());		
	}
	
	@Test
	void testSetEindkruispunt() {
		kardinaalMercierlaan.setEindkruispunt(kantineplein);
		assertSame(kantineplein, kardinaalMercierlaan.getEindkruispunt());
		assertEquals(Set.of(kardinaalMercierlaan), kantineplein.getAankomendeWegen());
	}
	
	@Test
	void testClearStartkruispunt() {
		kardinaalMercierlaan.setStartkruispunt(naamsepoort);
		kardinaalMercierlaan.clearStartkruispunt();
		assertNull(kardinaalMercierlaan.getStartkruispunt());
		assertEquals(Set.of(), naamsepoort.getVertrekkendeWegen());
	}
	
	@Test
	void testClearEindkruispunt() {
		kardinaalMercierlaan.setEindkruispunt(kantineplein);
		kardinaalMercierlaan.clearEindkruispunt();
		assertNull(kardinaalMercierlaan.getEindkruispunt());
		assertEquals(Set.of(), kantineplein.getAankomendeWegen());
	}
	
	@Test
	void testGetVolgendeWegenVanTweedeOrde() {
		Weg bondgenotenlaan = new Weg();
		Kruispunt groteMarkt = new Kruispunt();
		bondgenotenlaan.setEindkruispunt(groteMarkt);
		Weg tiensestraat = new Weg();
		tiensestraat.setStartkruispunt(groteMarkt);
		Kruispunt tiensepoort = new Kruispunt();
		tiensestraat.setEindkruispunt(tiensepoort);
		Weg tiensesteenweg = new Weg();
		tiensesteenweg.setStartkruispunt(tiensepoort);
		Weg naamsestraat = new Weg();
		naamsestraat.setStartkruispunt(groteMarkt);
		naamsestraat.setEindkruispunt(naamsepoort);
		kardinaalMercierlaan.setStartkruispunt(naamsepoort);
		Weg naamsesteenweg = new Weg();
		naamsesteenweg.setStartkruispunt(naamsepoort);
		
		assertEquals(Set.of(naamsesteenweg, kardinaalMercierlaan, tiensesteenweg), bondgenotenlaan.getVolgendeWegenVanTweedeOrde());
	}

}
