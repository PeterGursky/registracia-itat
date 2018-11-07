package sk.upjs.registracia_itat.persitent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.registracia_itat.entity.Workshop;

class MysqlWorkshopDaoTest {

	@Test
	void testGetAll() {
		List<Workshop> workshops = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		assertNotNull(workshops);
		assertTrue(workshops.size() > 0);
	}
	
	@Test
	void testSave() {
		Workshop cidmWorkshop = new Workshop();
		cidmWorkshop.setName("CIDM");
		cidmWorkshop.setStart(LocalDate.of(2018, 10, 5));
		cidmWorkshop.setEnd(LocalDate.of(2018, 11, 5));
		cidmWorkshop.setPriceFull(355);
		cidmWorkshop.setPriceStudent(295);
		cidmWorkshop.setPriceFullLate(380);
		cidmWorkshop.setPriceStudentLate(320);
		//create
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		assertNotNull(cidmWorkshop.getId());
		cidmWorkshop.setName("cidm-nove");
		//update
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		List<Workshop> all = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		for (Workshop w : all) {
			if (w.getId() == cidmWorkshop.getId()) {
				assertEquals("cidm-nove", w.getName());
				DaoFactory.INSTANCE.getWorkshopDao().delete(w.getId());
				return;
			}
		}
		assertTrue(false,"update sa nepodaril");
	}
	
	@Test
	void deleteTest() {
		Workshop cidmWorkshop = new Workshop();
		cidmWorkshop.setName("CIDM");
		cidmWorkshop.setStart(LocalDate.of(2018, 10, 5));
		cidmWorkshop.setEnd(LocalDate.of(2018, 11, 5));
		cidmWorkshop.setPriceFull(355);
		cidmWorkshop.setPriceStudent(295);
		cidmWorkshop.setPriceFullLate(380);
		cidmWorkshop.setPriceStudentLate(320);
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		Long id = cidmWorkshop.getId();
		DaoFactory.INSTANCE.getWorkshopDao().delete(id);
		List<Workshop> all = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		for (Workshop w: all) {
			assertNotEquals(id, w.getId());
		}
	}
}
