package sk.upjs.registracia_itat;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantTest {

	WorkShop cidmWorkshop;
	WorkShop slonlpWorkshop;
	
	public ParticipantTest() {
		cidmWorkshop = new WorkShop();
		cidmWorkshop.setPriceFull(355);
		cidmWorkshop.setPriceStudent(295);
		cidmWorkshop.setPriceFullLate(380);
		cidmWorkshop.setPriceStudentLate(320);
		slonlpWorkshop = new WorkShop();
		slonlpWorkshop.setPriceFull(245);
		slonlpWorkshop.setPriceStudent(195);
		slonlpWorkshop.setPriceFullLate(265);
		slonlpWorkshop.setPriceStudentLate(210);
	}
	
	@Test
	void testFinalPrice1() {
		Participant participant = new Participant();
		participant.setEarly(true);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		System.out.println(participant.finalPrice());
		
		assertEquals(355.0, participant.finalPrice());
	}

	@Test
	void testFinalPrice2() {
		Participant participant = new Participant();
		participant.setEarly(false);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		assertEquals(380.0, participant.finalPrice());
	}

	@Test
	void testFinalPrice3() {
		Participant participant = new Participant();
		participant.setEarly(true);
		participant.setWorkshop(slonlpWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		List<Companion> companions = new ArrayList<>();
		Companion c1 = new Companion();
		c1.setCategory(CompanionCategory.ADULT);
		c1.setStart(LocalDateTime.of(2019, 9, 21, 17, 0));
		c1.setEnd(LocalDateTime.of(2019, 9, 23, 10, 0));
		c1.setTshirt(null);
		companions.add(c1);
		participant.setCompanions(companions);
		assertEquals(245 + 110, participant.finalPrice());
	}

	@Test
	void testFinalPrice4() {
		Participant participant = new Participant();
		participant.setEarly(false);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(true);
		participant.setCash(false);
		assertEquals(455.0, participant.finalPrice());
	}
}
