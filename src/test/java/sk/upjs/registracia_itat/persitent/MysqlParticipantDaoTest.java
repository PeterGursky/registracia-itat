package sk.upjs.registracia_itat.persitent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.registracia_itat.entity.Participant;
import sk.upjs.registracia_itat.entity.Tshirt;

class MysqlParticipantDaoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAll() {
		List<Participant> list = DaoFactory.INSTANCE.getParticipantDao().getAll();
		System.out.println(list);
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Test
	void testSave() {
		Participant participant = new Participant();
		participant.setName("XX");
		participant.setSurname("X$39_44");
		participant.setEmail("XX@");
		participant.setAddress("dummyaddress");
		participant.setStart(LocalDateTime.of(2018,12,20,12,0));
		participant.setEnd(LocalDateTime.of(2018,12,21,15,0));
		participant.setCash(false);
		participant.setEarly(false);
		participant.setOrganization("dummyorg");
		participant.setSingleRoom(true);
		participant.setStudent(false);
		participant.setTshirt(Tshirt.M);
		participant.setWorkshop(DaoFactory.INSTANCE.getWorkshopDao().getAll().get(0));
		DaoFactory.INSTANCE.getParticipantDao().add(participant);
		assertNotNull(participant.getId());
		participant.setName("YY");
		DaoFactory.INSTANCE.getParticipantDao().save(participant);
		List<Participant> participants = DaoFactory.INSTANCE.getParticipantDao().getAll();
		for (Participant p: participants) {
			if (p.getId().equals(participant.getId())) {
				assertEquals("YY", p.getName());
				DaoFactory.INSTANCE.getParticipantDao().delete(p.getId());
				return;
			}
		}
		assertTrue(false,"update sa nepodaril");
	}

}
